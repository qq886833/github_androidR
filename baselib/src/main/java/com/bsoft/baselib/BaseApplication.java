package com.bsoft.baselib;

import android.content.Context;

import com.bsoft.baselib.widget.loadingstatusview.Gloading;
import com.bsoft.baselib.widget.loadingstatusview.adapter.GlobalAdapter;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

public class BaseApplication extends MultiDexApplication {


    @Override
    public void onCreate() {
        super.onCreate();

        Gloading.debug(BuildConfig.DEBUG);
        Gloading.initDefault(new GlobalAdapter());
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }
}
