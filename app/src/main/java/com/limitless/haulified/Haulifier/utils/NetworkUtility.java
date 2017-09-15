package com.limitless.haulified.Haulifier.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtility {
    public static boolean isNetworkConnectionAvailable(Context context) {
        boolean isNetworkConnectionAvailable = false;

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");

        isNetworkConnectionAvailable = (connectivityManager.getActiveNetworkInfo() != null &&
                connectivityManager.getActiveNetworkInfo().isAvailable() &&
                connectivityManager.getActiveNetworkInfo().isConnected());

        return isNetworkConnectionAvailable;
    }
}
