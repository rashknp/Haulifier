package com.limitless.haulified.Haulifier.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.limitless.haulified.Haulifier.utils.LogUtils;

public class Preference {
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;

    public static String DEVICE_DISPLAY_WIDTH = "DEVICE_DISPLAY_WIDTH";
    public static String DEVICE_DISPLAY_HEIGHT = "DEVICE_DISPLAY_HEIGHT";


    public static String token = "token";
    public static String REMEMBER_ME = "REMEMBER_ME";
    public static String USER_NAME = "USER_NAME";
    public static String PASSWORD = "PASSWORD";
    public static String EMAILID = "EMAILID";
    public static String NAME = "NAME";
    public static String CUSTOMERID = "CUSTOMERID";
    public static String dob = "dob";
    public static String add1 = "add1";
    public static String add2 = "add2";
    public static String add3 = "add3";
    public static String city = "city";
    public static String country = "country";
    public static String phn = "phone";

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        Preference.token = token;
    }

    public Preference(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        edit = preferences.edit();
    }

    public void saveStringInPreference(String strKey, String strValue) {
        edit.putString(strKey, strValue);
    }

    public void saveIntInPreference(String strKey, int value) {
        edit.putInt(strKey, value);
    }

    public void saveBooleanInPreference(String strKey, boolean value) {
        edit.putBoolean(strKey, value);
    }

    public void saveLongInPreference(String strKey, Long value) {
        edit.putLong(strKey, value);
    }

    public void saveDoubleInPreference(String strKey, String value) {
        LogUtils.e("value", "" + value);
        edit.putString(strKey, value);
    }

    public void removeFromPreference(String strKey) {
        edit.remove(strKey);
    }

    public void commitPreference() {
        edit.commit();
    }

    public String getStringFromPreference(String strKey, String defaultValue) {
        return preferences.getString(strKey, defaultValue);
    }

    public boolean getbooleanFromPreference(String strKey, boolean defaultValue) {
        return preferences.getBoolean(strKey, defaultValue);
    }

    public int getIntFromPreference(String strKey, int defaultValue) {
        return preferences.getInt(strKey, defaultValue);
    }

    public double getDoubleFromPreference(String strKey, double defaultValue) {
        return Double.parseDouble(preferences.getString(strKey, ""
                + defaultValue));
    }

    public long getLongInPreference(String strKey) {
        return preferences.getLong(strKey, 0);
    }
}
