package com.adsnativetamplete.retrofit.models.appupdatesettings;

import com.google.gson.annotations.SerializedName;

public class GetProfile {

    @SerializedName("cancel")
    private boolean cancel;

    @SerializedName("date")
    private String date;

    @SerializedName("appLink")
    private String appLink;

    @SerializedName("applicationMasterId")
    private String applicationMasterId;

    @SerializedName("isDelete")
    private boolean isDelete;

    @SerializedName("__v")
    private int v;

    @SerializedName("appUpdatePopUp")
    private boolean appUpdatePopUp;

    @SerializedName("description")
    private String description;

    @SerializedName("_id")
    private String id;

    @SerializedName("version")
    private String version;

    public boolean isCancel() {
        return cancel;
    }

    public String getDate() {
        return date;
    }

    public String getAppLink() {
        return appLink;
    }

    public String getApplicationMasterId() {
        return applicationMasterId;
    }

    public boolean isIsDelete() {
        return isDelete;
    }

    public int getV() {
        return v;
    }

    public boolean isAppUpdatePopUp() {
        return appUpdatePopUp;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }
}