package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class OtherInputFieldsItem {

    @SerializedName("fieldName")
    private String fieldName;

    @SerializedName("isGlobal")
    private boolean isGlobal;

    @SerializedName("_id")
    private String id;

    @SerializedName("value")
    private String value;

    @SerializedName("fieldId")
    private String fieldId;

    public String getFieldName() {
        return fieldName;
    }

    public boolean isIsGlobal() {
        return isGlobal;
    }

    public String getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getFieldId() {
        return fieldId;
    }
}