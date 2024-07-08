package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdsDetailsResponse {

    @SerializedName("getProfile")
    private List<GetProfileItem> getProfile;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public List<GetProfileItem> getGetProfile() {
        return getProfile;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }
}