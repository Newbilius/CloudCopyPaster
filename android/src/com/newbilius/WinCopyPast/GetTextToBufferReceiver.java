package com.newbilius.WinCopyPast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.ClipboardManager;
import android.widget.Toast;
import roboguice.receiver.RoboBroadcastReceiver;

@SuppressWarnings("deprecation")
public class GetTextToBufferReceiver extends RoboBroadcastReceiver {
    ClipboardManager clipboardManager;
    @Override
    public void handleReceive(Context context, Intent intent) {
        clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        clipboardManager.setText(GcmBroadcastReceiver.textToCopy);
        Toast.makeText(context, context.getString(R.string.text_coped), Toast.LENGTH_SHORT).show();
    }
}