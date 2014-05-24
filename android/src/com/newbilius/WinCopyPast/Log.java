package com.newbilius.WinCopyPast;

public class Log {
    private static final String DebugKey="hhh";

    public static void Debug(String s){
        //android.util.Log.d(DebugKey,s);
    }

    public static void Error(String s){
        android.util.Log.e(DebugKey,s);
    }

    public static void Error(Error e){
        android.util.Log.e(DebugKey,e.getMessage());
    }
}