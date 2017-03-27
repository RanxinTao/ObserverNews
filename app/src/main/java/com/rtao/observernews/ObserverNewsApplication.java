package com.rtao.observernews;

import android.app.Application;

import org.xutils.x;

public class ObserverNewsApplication extends Application {

    /**
     * This function is executed when the application is started
     */
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // output debug log or not
    }
}
