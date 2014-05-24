package com.newbilius.WinCopyPast;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.ClipboardManager;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.inject.Inject;
import roboguice.receiver.RoboBroadcastReceiver;

public class GcmBroadcastReceiver extends RoboBroadcastReceiver{

    @Inject
    Options options;
    Context context;
    public static String textToCopy="";

    @Override
    public void handleReceive(Context context, Intent intent) {
        this.context=context;
        if(!options.getNotificationId().isEmpty()){
            Bundle extras = intent.getExtras();
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);

            String messageType = gcm.getMessageType(intent);
            if ((extras != null && !extras.isEmpty())) {
                if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                    String text=extras.getString("text");
                    sendNotification(42,text);
                }
            }
        }

        setResultCode(Activity.RESULT_OK);
    }

    @SuppressWarnings("deprecation")
    private void sendNotification(int NotificationId,String msg) {
        Analytics.getTracker(context).setScreenName("NOTIFICATION");
        Analytics.getTracker(context).send(new HitBuilders.AppViewBuilder().build());

        NotificationManager mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        textToCopy=msg;

        int defaults=0;
        if (options.getSound()){
            defaults=defaults|Notification.DEFAULT_SOUND;
        }
        if (options.getVibration()){
            defaults=defaults|Notification.DEFAULT_VIBRATE;
        }
        if (options.getInstantCopy()){
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(GcmBroadcastReceiver.textToCopy);
        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.notification)
                        .setContentTitle(context.getString(R.string.new_text))
                        .setDefaults(defaults)
                        .setTicker(context.getString(R.string.new_text))
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg)
                        .setAutoCancel(true);


        PendingIntent contentIntent = PendingIntent.getBroadcast(context, 0, new Intent("com.newbilius.WinCopyPas.SEND_TO_BUFFER"), 0);
        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NotificationId, mBuilder.build());
    }
}