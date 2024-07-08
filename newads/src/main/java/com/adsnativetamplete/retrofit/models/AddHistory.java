package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class AddHistory{

	@SerializedName("features")
	private String features;

	@SerializedName("historyDate")
	private String historyDate;

	@SerializedName("remark")
	private String remark;

	@SerializedName("historyInstallation")
	private String historyInstallation;

	@SerializedName("version")
	private String version;

	public void setFeatures(String features){
		this.features = features;
	}

	public String getFeatures(){
		return features;
	}

	public void setHistoryDate(String historyDate){
		this.historyDate = historyDate;
	}

	public String getHistoryDate(){
		return historyDate;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setHistoryInstallation(String historyInstallation){
		this.historyInstallation = historyInstallation;
	}

	public String getHistoryInstallation(){
		return historyInstallation;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}
}