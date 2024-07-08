package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class PlayConsoleId{

	@SerializedName("date")
	private String date;

	@SerializedName("number")
	private String number;

	@SerializedName("password")
	private String password;

	@SerializedName("imageName")
	private String imageName;

	@SerializedName("isDelete")
	private boolean isDelete;

	@SerializedName("__v")
	private int v;

	@SerializedName("name")
	private String name;

	@SerializedName("_id")
	private String id;

	@SerializedName("deviceName")
	private String deviceName;

	@SerializedName("url")
	private String url;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private String status;

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

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setDeviceName(String deviceName){
		this.deviceName = deviceName;
	}

	public String getDeviceName(){
		return deviceName;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}