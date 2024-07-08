package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class AdsCount {

    @SerializedName("rewarded")
    private String rewarded;

    @SerializedName("interstitial")
    private String interstitial;

    @SerializedName("adsFix")
    private boolean adsFix;

    public String getRewarded() {
        return rewarded;
    }

    public String getInterstitial() {
        return interstitial;
    }

    public boolean isAdsFix() {
        return adsFix;
    }
}