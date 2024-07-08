package com.adsnativetamplete;

import android.content.Context;
import android.content.SharedPreferences;

public class NativePreferenceUtil {

    String counterKey = "counter";
    String counterIntersKey = "counterIntersKey";
    String counterRewardsKey = "counterRewardsKey";
    String counterNativeKey = "counterNativeKey";
    String counterAppOpenKey = "counterAppOpenKey";
    String AdsDetailsResponse = "AdsDetailsResponse";

    SharedPreferences preferences;

    public NativePreferenceUtil(Context context) {
        preferences = context.getSharedPreferences("MyRating", Context.MODE_PRIVATE);
    }

    public void setCountRate() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(counterKey, preferences.getInt(counterKey, -1) + 1);
        editor.apply();
    }

    public boolean isRatingShow() {
        return preferences.getInt(counterKey, 0) % 10 == 0;
    }

    public void setIntersCount() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(counterIntersKey, preferences.getLong(counterIntersKey, 0) + 1);
        editor.apply();
    }

    public long getIntersCount() {
        return preferences.getLong(counterIntersKey, 1);
    }


    public void setRewardsCount() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(counterRewardsKey, preferences.getLong(counterRewardsKey, 0) + 1);
        editor.apply();
    }

    public long getRewardsCount() {
        return preferences.getLong(counterRewardsKey, 1);
    }


    public void setNativeCount() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(counterNativeKey, preferences.getLong(counterNativeKey, 0) + 1);
        editor.apply();
    }

    public long getNativeCount() {
        return preferences.getLong(counterNativeKey, 1);
    }


    public void setAppOpenCount() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(counterAppOpenKey, preferences.getLong(counterAppOpenKey, 0) + 1);
        editor.apply();
    }

    public long getAppOpenCount() {
        return preferences.getLong(counterAppOpenKey, 1);
    }

    public String getAdsId() {
        return preferences.getString(AdsDetailsResponse, "");
    }

    public void setAdsId(String defaultVal) {
        preferences.edit().putString(AdsDetailsResponse, defaultVal).apply();
    }
}
