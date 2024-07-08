package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class DeveloperMasterId{

	@SerializedName("date")
	private String date;

	@SerializedName("number")
	private String number;

	@SerializedName("imageName")
	private String imageName;

	@SerializedName("isDelete")
	private boolean isDelete;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	@SerializedName("technology")
	private String technology;

	@SerializedName("developerName")
	private String developerName;

	@SerializedName("status")
	private boolean status;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setImageName(String imageName){
		this.imageName = imageName;
	}

	public String getImageName(){
		return imageName;
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

	public void setTechnology(String technology){
		this.technology = technology;
	}

	public String getTechnology(){
		return technology;
	}

	public void setDeveloperName(String developerName){
		this.developerName = developerName;
	}

	public String getDeveloperName(){
		return developerName;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}
}