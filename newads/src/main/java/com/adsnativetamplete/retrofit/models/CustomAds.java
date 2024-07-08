package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class CustomAds {

    @SerializedName("descriptionTwoAds")
    private String descriptionTwoAds;

    @SerializedName("customImageSmall")
    private String customImageSmall;

    @SerializedName("descriptionOneAds")
    private String descriptionOneAds;

    @SerializedName("playStoreUrl")
    private String playStoreUrl;

    @SerializedName("appNameAds")
    private String appNameAds;

    @SerializedName("customImageBig")
    private String customImageBig;

    @SerializedName("buttonTxt")
    private String buttonTxt;

    public String getDescriptionTwoAds() {
        return descriptionTwoAds;
    }

    public String getCustomImageSmall() {
        return customImageSmall;
    }

    public String getDescriptionOneAds() {
        return descriptionOneAds;
    }

    public String getPlayStoreUrl() {
        return playStoreUrl;
    }

    public String getAppNameAds() {
        return appNameAds;
    }

    public String getCustomImageBig() {
        return customImageBig;
    }

    public String getButtonTxt() {
        return buttonTxt;
    }
}