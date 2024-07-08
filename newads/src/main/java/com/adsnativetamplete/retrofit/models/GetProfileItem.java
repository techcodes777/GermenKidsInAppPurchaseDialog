package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProfileItem {

    @SerializedName("date")
    private String date;

    @SerializedName("adxFour")
    private AdxFour adxFour;

    @SerializedName("adxTwo")
    private AdxTwo adxTwo;

    @SerializedName("adxOne")
    private AdxOne adxOne;

    @SerializedName("customAds")
    private CustomAds customAds;

    @SerializedName("applicationMasterId")
    private String applicationMasterId;

    @SerializedName("__v")
    private int v;

    @SerializedName("googleAdmob")
    private GoogleAdmob googleAdmob;

    @SerializedName("adxFive")
    private AdxFive adxFive;

    @SerializedName("_id")
    private String id;

    @SerializedName("otherInputFields")
    private List<OtherInputFieldsItem> otherInputFields;

    @SerializedName("adsCount")
    private AdsCount adsCount;

    @SerializedName("firstScreenSetting")
    private FirstScreenSetting firstScreenSetting;

    @SerializedName("adxThree")
    private AdxThree adxThree;

    public String getDate() {
        return date;
    }

    public AdxFour getAdxFour() {
        return adxFour;
    }

    public AdxTwo getAdxTwo() {
        return adxTwo;
    }

    public AdxOne getAdxOne() {
        return adxOne;
    }

    public CustomAds getCustomAds() {
        return customAds;
    }

    public String getApplicationMasterId() {
        return applicationMasterId;
    }

    public int getV() {
        return v;
    }

    public GoogleAdmob getGoogleAdmob() {
        return googleAdmob;
    }

    public AdxFive getAdxFive() {
        return adxFive;
    }

    public String getId() {
        return id;
    }

    public List<OtherInputFieldsItem> getOtherInputFields() {
        return otherInputFields;
    }

    public AdsCount getAdsCount() {
        return adsCount;
    }

    public FirstScreenSetting getFirstScreenSetting() {
        return firstScreenSetting;
    }

    public AdxThree getAdxThree() {
        return adxThree;
    }
}