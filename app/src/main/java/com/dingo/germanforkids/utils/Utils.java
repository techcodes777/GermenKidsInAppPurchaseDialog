package com.dingo.germanforkids.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {

    static SharedPreferences sharedPreferences;

    public Utils(Context context) {
        sharedPreferences = context.getSharedPreferences("MyDingoPref", 0);
    }

    public static void setPref(String Key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key, value);
        editor.apply();
    }

    public static Boolean getPref(String Key, boolean value) {
        return sharedPreferences.getBoolean(Key, value);
    }


}
