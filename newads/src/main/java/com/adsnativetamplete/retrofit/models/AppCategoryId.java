package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class AppCategoryId{

	@SerializedName("applicationCategory")
	private String applicationCategory;

	@SerializedName("date")
	private String date;

	@SerializedName("isDelete")
	private boolean isDelete;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	public void setApplicationCategory(String applicationCategory){
		this.applicationCategory = applicationCategory;
	}

	public String getApplicationCategory(){
		return applicationCategory;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setIsDelete(boolean isDelete){
		this.isDelete = isDelete;
	}

	public boolean isIsDelete(){
		return isDelete;
	}

	public void setV(int v){
		this.v = v;
	}

	public int getV(){
		return v;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}