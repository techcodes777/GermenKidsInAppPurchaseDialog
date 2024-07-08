package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class CategoryItem {

	@SerializedName("date")
	private String date;

	@SerializedName("uploadInstallation")
	private String uploadInstallation;

	@SerializedName("reason")
	private String reason;

	@SerializedName("appVersion")
	private String appVersion;

	@SerializedName("imageName")
	private String imageName;

	@SerializedName("developerMasterId")
	private DeveloperMasterId developerMasterId;

	@SerializedName("addHistory")
	private AddHistory addHistory;

	@SerializedName("url")
	private String url;

	@SerializedName("uploadDate")
	private String uploadDate;

	@SerializedName("__v")
	private int v;

	@SerializedName("name")
	private String name;

	@SerializedName("playConsoleId")
	private PlayConsoleId playConsoleId;

	@SerializedName("referenceById")
	private ReferenceById referenceById;

	@SerializedName("_id")
	private String id;

	@SerializedName("txtUrl")
	private String txtUrl;

	@SerializedName("referenceApp")
	private String referenceApp;

	@SerializedName("email")
	private String email;

	@SerializedName("appCategoryId")
	private AppCategoryId appCategoryId;

	@SerializedName("status")
	private String status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setUploadInstallation(String uploadInstallation){
		this.uploadInstallation = uploadInstallation;
	}

	public String getUploadInstallation(){
		return uploadInstallation;
	}

	public void setReason(String reason){
		this.reason = reason;
	}

	public String getReason(){
		return reason;
	}

	public void setAppVersion(String appVersion){
		this.appVersion = appVersion;
	}

	public String getAppVersion(){
		return appVersion;
	}

	public void setImageName(String imageName){
		this.imageName = imageName;
	}

	public String getImageName(){
		return imageName;
	}

	public void setDeveloperMasterId(DeveloperMasterId developerMasterId){
		this.developerMasterId = developerMasterId;
	}

	public DeveloperMasterId getDeveloperMasterId(){
		return developerMasterId;
	}

	public void setAddHistory(AddHistory addHistory){
		this.addHistory = addHistory;
	}

	public AddHistory getAddHistory(){
		return addHistory;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setUploadDate(String uploadDate){
		this.uploadDate = uploadDate;
	}

	public String getUploadDate(){
		return uploadDate;
	}

	public void setV(int v){
		this.v = v;
	}

	public int getV(){
		return v;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPlayConsoleId(PlayConsoleId playConsoleId){
		this.playConsoleId = playConsoleId;
	}

	public PlayConsoleId getPlayConsoleId(){
		return playConsoleId;
	}

	public void setReferenceById(ReferenceById referenceById){
		this.referenceById = referenceById;
	}

	public ReferenceById getReferenceById(){
		return referenceById;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setTxtUrl(String txtUrl){
		this.txtUrl = txtUrl;
	}

	public String getTxtUrl(){
		return txtUrl;
	}

	public void setReferenceApp(String referenceApp){
		this.referenceApp = referenceApp;
	}

	public String getReferenceApp(){
		return referenceApp;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAppCategoryId(AppCategoryId appCategoryId){
		this.appCategoryId = appCategoryId;
	}

	public AppCategoryId getAppCategoryId(){
		return appCategoryId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}