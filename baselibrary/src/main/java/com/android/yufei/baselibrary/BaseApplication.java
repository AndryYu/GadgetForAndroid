package com.android.yufei.baselibrary;

import android.app.Application;

import com.android.yufei.baselibrary.common.utils.AppCrashHandler;

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCrashHandler.getInstance().setCrashHandler(this);

    }
}
