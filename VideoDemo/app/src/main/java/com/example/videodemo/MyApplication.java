package com.example.videodemo;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.support.multidex.MultiDex;

import com.aliyun.common.crash.CrashHandler;
import com.aliyun.common.httpfinal.QupaiHttpFinal;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

//        loadLibs();
        QupaiHttpFinal.getInstance().initOkHttpFinal();
        com.aliyun.vod.common.httpfinal.QupaiHttpFinal.getInstance().initOkHttpFinal();
        //Logger.setDebug(true);
        //        localCrashHandler();
        //        new NativeCrashHandler().registerForNativeCrash(this);

        initLeakcanary();//初始化内存检测

    }

    private void loadLibs(){
        System.loadLibrary("live-openh264");
        System.loadLibrary("fdk-aac");
        System.loadLibrary("svideo_alivcffmpeg");
    }

    private void localCrashHandler() {
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
    }

    private void initLeakcanary() {
        //LeakCanary.install(this);
    }

}
