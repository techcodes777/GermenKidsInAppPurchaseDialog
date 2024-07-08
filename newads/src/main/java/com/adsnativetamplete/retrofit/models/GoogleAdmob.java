package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class GoogleAdmob {

    @SerializedName("appOpen")
    private String appOpen;

    @SerializedName("native")
    private String jsonMemberNative;

    @SerializedName("rewarded")
    private String rewarded;

    @SerializedName("interstitial")
    private String interstitial;

    @SerializedName("banner")
    private String banner;

    public String getAppOpen() {
        return appOpen;
    }

    public String getJsonMemberNative() {
        return jsonMemberNative;
    }

    public String getRewarded() {
        return rewarded;
    }

    public String getInterstitial() {
        return interstitial;
    }

    public String getBanner() {
        return banner;
    }
}