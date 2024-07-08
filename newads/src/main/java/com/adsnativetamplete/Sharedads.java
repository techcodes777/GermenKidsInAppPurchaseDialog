package com.adsnativetamplete;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Sharedads {
    private volatile static Sharedads mInstance;
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public Sharedads(Context context) {
        appSharedPrefs = context.getSharedPreferences("ads_pref", Activity.MODE_PRIVATE);
        prefsEditor = appSharedPrefs.edit();
    }

    public Sharedads() {
    }

    public static Sharedads getInstance() {
        if (null == mInstance) {
            synchronized (Sharedads.class) {
                if (null == mInstance) {
                    mInstance = new Sharedads();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context) {
        if (context == null) {
            appSharedPrefs = context.getSharedPreferences("ads_pref", Activity.MODE_PRIVATE);
            prefsEditor = appSharedPrefs.edit();
        }

        if (appSharedPrefs == null) {
            appSharedPrefs = context.getSharedPreferences("ads_pref", Activity.MODE_PRIVATE);
            prefsEditor = appSharedPrefs.edit();
        }
    }

    public boolean checkPreferenceSet(String key_value) {
        return appSharedPrefs.contains(key_value);
    }

    public boolean getBool(String key_value, boolean default_value) {
        return appSharedPrefs.getBoolean(key_value, default_value);
    }

    public void setBool(String key_value, boolean default_value) {
        prefsEditor.putBoolean(key_value, default_value).commit();
    }

    public int getInt(String key_value) {
        return appSharedPrefs.getInt(key_value, 0);
    }

    public void setInt(String key_value, int default_value) {
        prefsEditor.putInt(key_value, default_value).commit();
    }

    public String getString(String key_value, String default_value) {
        return appSharedPrefs.getString(key_value, default_value);
    }

    public String getString(String key_value) {
        return appSharedPrefs.getString(key_value, "");
    }

    public void setString(String key_value, String default_value) {
        prefsEditor.putString(key_value, default_value).commit();
    }

    public long getLong(String key_value) {
        return appSharedPrefs.getLong(key_value, -1);
    }

    public void setLong(String key_value, Long default_value) {
        prefsEditor.putLong(key_value, default_value).commit();
    }
}
