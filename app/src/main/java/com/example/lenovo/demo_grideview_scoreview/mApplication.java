package com.example.lenovo.demo_grideview_scoreview;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by lenovo on 2016/8/31.
 */
public class mApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        mContext=getApplicationContext();
        super.onCreate();
    }

}
