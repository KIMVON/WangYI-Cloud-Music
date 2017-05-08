package com.example.wang_yi_cloudmusic.app;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePal;

/**
 * Created by 79069 on 2017/4/29.
 */

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        sContext = getApplicationContext();

    }

    public static Context getContext(){
        return sContext;
    }
}
