package com.visz.tv.utils;

import static com.visz.tv.BuildConfig.LOG_OPEN;

import android.util.Log;

public class LogUtil {
    private static final String TAG = "icast";

    public static void i(String msg) {
        if (LOG_OPEN) {
            Log.i(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (LOG_OPEN) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (LOG_OPEN) {
            Log.e(TAG, msg);
        }
    }

    // 可以添加其他类型的 log 方法，如 w(), v() 等。
}