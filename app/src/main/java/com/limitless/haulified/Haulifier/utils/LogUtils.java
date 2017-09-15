package com.limitless.haulified.Haulifier.utils;

import android.util.Log;

public class LogUtils {
    public static boolean isLogEnable = false;

    public static void e(String tag, String msg) {
        if (isLogEnable)
            Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isLogEnable)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isLogEnable)
            Log.v(tag, msg);
    }


    public static void d(String tag, String msg) {
        if (isLogEnable)
            Log.d(tag, msg);
    }

    public static void setLogUtilsEnable(boolean isEnable) {
        isLogEnable = isEnable;
    }

}
