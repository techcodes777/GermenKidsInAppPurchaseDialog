package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class ReferenceById{

	@SerializedName("date")
	private String date;

	@SerializedName("number")
	private String number;

	@SerializedName("imageName")
	private String imageName;

	@SerializedName("isDelete")
	private boolean isDelete;

	@SerializedName("companyName")
	private String companyName;

	@SerializedName("__v")
	private int v;

	@SerializedName("_id")
	private String id;

	@SerializedName("email")
	private String email;

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

	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}

	public String getCompanyName(){
		return companyName;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}
}