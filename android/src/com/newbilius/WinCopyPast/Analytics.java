package com.newbilius.WinCopyPast;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class Analytics {

    public static Tracker mTracker = null;

    public static synchronized Tracker getTracker(Context context) {

        if (mTracker==null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(context);
            //mTracker=analytics.newTracker("TRACKER CODE");
			//set if you need you one tracker
        }

        return mTracker;
    }
}