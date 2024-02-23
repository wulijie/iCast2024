package com.visz.tv;

import androidx.multidex.MultiDexApplication;


public class App extends MultiDexApplication {
    private static App inst;

    public static App inst() {
        return inst;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inst = this;
    }
}
