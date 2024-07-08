package com.adsnativetamplete.retrofit.models.appupdatesettings;

import com.google.gson.annotations.SerializedName;

public class AppUpdateResponse {

    @SerializedName("getProfile")
    private GetProfile getProfile;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public GetProfile getGetProfile() {
        return getProfile;
    }

    public boolean isIsSuccess() {
        return isSuccess;
    }
}