package com.newbilius.WinCopyPast;

import android.app.Activity;
import android.content.Context;

import javax.inject.Inject;

public class ConfigActivityController {
    @Inject Options options;
    @Inject PushNotification pushNotification;

    public boolean GetStatus(){
        return !options.getNotificationId().isEmpty();
    }

    public String GetButtonText(Context context){
        if (!options.getNotificationId().isEmpty()){
            return context.getResources().getString(R.string.button_on);
        }else{
            return context.getResources().getString(R.string.button_off);
        }
    }

    public void ChangeParams(String login,
                             String password,
                             Activity activity,
                             OnComplete onComplete){
        boolean status=GetStatus();
        if (login.isEmpty() || password.isEmpty()){
            onComplete.Error(activity.getBaseContext().getString(R.string.error_empty_field));
        }else{
            options.setParams(login,password);

            if (status){
                options.setNotificationId("");
                onComplete.Complete();
            }else{
                pushNotification.register(activity,Options.SENDER_ID,options.getNotificationId(),onComplete);
            }
        }
    }
}