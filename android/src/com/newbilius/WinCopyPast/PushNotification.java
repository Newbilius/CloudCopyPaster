package com.newbilius.WinCopyPast;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import javax.inject.Inject;
import java.io.IOException;

public class PushNotification {

    private Activity activity;
    private String senderId;
    public OnComplete Complete;

    @Inject ConnectivityManager connectivityManager;
    @Inject
    Options options;

    Net net=new Net();

    private boolean HaveInternet(){
        NetworkInfo nInfo = connectivityManager.getActiveNetworkInfo();
        return nInfo != null && nInfo.isConnected();
    }

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                if (resultCode==ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED){
                    return true;
                }
                try{
                    if (activity!=null){
                        GooglePlayServicesUtil.getErrorDialog(resultCode, activity,PLAY_SERVICES_RESOLUTION_REQUEST).show();
                    }

                }catch (Error e){
                    Log.Error(e);
                }

            }
            return false;
        }
        return true;
    }
    String tmpId;
    int repeatCounter=0;
    public void register(Activity activity,String senderId,String notificationId,OnComplete complete){
        this.activity=activity;
        this.senderId=senderId;
        this.Complete =complete;

        Complete.Process("");

        if (checkPlayServices()) {
            if (notificationId.isEmpty()) {
                repeatCounter=0;
                RegisterPushInTask registerInBackgroundTask=new RegisterPushInTask();
                registerInBackgroundTask.execute();
            }
        }
    }

    class RegisterPushInTask extends AsyncTask<Void,Void,Void> {

        String msg="";
        String pushRegId="";
        boolean ok=false;

        @Override
        protected Void doInBackground(Void... params) {
            Thread.currentThread().setName("RegisterPushInTask");
            try {
                GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(activity);
                pushRegId = gcm.register(senderId);
                msg = "push Device registered, registration ID=" + pushRegId;

            } catch (IOException ex) {
                msg = "push Error :" + ex.getMessage();
            }

            if (!msg.contains("Error") && !msg.contains("SERVICE_NOT_AVAILABLE")){
                ok=SaveToServer(pushRegId);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (msg.contains("SERVICE_NOT_AVAILABLE")){
                repeatCounter++;
                if (repeatCounter<10){
                    if (HaveInternet()){
                        RegisterPushInTask registerInBackgroundTask=new RegisterPushInTask();
                        registerInBackgroundTask.execute();
                        Log.Error(msg+" Но мы продолжаем!");
                    }else{
                        Complete.Error("Нет интернета.");
                        repeatCounter=0;
                    }
                }else{
                    Complete.Error("Гугл-сервис не отвечает.");
                    repeatCounter=0;
                }
            }else{
                if (msg.contains("Error")){
                    Log.Error(msg);
                    Complete.Error("Ошибка подключения: "+msg);
                }else{
                    if (ok){
                        Complete.Complete();
                    }else{
                        Complete.Error(net.ErrorText);
                    }
                }
            }
        }
    }

    private boolean SaveToServer(String pushRegId){
        boolean result;


        result=net.Execute(options.getLogin(),options.getPass(),pushRegId,activity.getBaseContext());
        if (result){
            options.setNotificationId(pushRegId);
        }

        return result;
    }

}