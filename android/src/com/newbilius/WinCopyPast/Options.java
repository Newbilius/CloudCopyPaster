package com.newbilius.WinCopyPast;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.inject.Inject;

public class Options {
//SET YOU SERVER SENDER ID!
    //public static final String SENDER_ID="";
    public static final String URL="http://cloud-paster.com/save.php";

    @Inject SharedPreferences ProgramOptions;

    public String getLogin(){
        return ProgramOptions.getString("Login", "");
    }

    public String getPass(){
        return ProgramOptions.getString("Password","" );
    }

    public boolean getVibration(){
        return ProgramOptions.getBoolean("Vibration", true);
    }

    public void setVibration(boolean value){
        SharedPreferences.Editor ed = ProgramOptions.edit();
        ed.putBoolean("Vibration",value);
        ed.commit();
    }

    public boolean getSound(){
        return ProgramOptions.getBoolean("Sound",true);
    }

    public void setSound(boolean value){
        SharedPreferences.Editor ed = ProgramOptions.edit();
        ed.putBoolean("Sound",value);
        ed.commit();
    }

    public boolean getInstantCopy(){
        return ProgramOptions.getBoolean("InstantCopy",false);
    }

    public void setInstantCopy(boolean value){
        SharedPreferences.Editor ed = ProgramOptions.edit();
        ed.putBoolean("InstantCopy",value);
        ed.commit();
    }

    public String getNotificationId(){
        return ProgramOptions.getString("KEY","" );
    }

    public void setNotificationId(String id){
        SharedPreferences.Editor ed = ProgramOptions.edit();
        ed.putString("KEY", id);
        ed.commit();
    }

    public void setParams(String login, String password){
        SharedPreferences.Editor ed = ProgramOptions.edit();
        ed.putString("Login",login);
        ed.putString("Password",password);
        ed.commit();
    }
}