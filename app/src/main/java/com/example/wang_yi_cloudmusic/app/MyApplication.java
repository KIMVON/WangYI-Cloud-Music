package com.example.wang_yi_cloudmusic.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by 79069 on 2017/4/29.
 */

public class MyApplication extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();

    }

    public static Context getContext(){
        return sContext;
    }
}
