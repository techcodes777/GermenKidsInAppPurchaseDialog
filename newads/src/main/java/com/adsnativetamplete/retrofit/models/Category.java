package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Category{

	@SerializedName("data")
	private ArrayList<CategoryItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("isSuccess")
	private boolean isSuccess;

	public void setData(ArrayList<CategoryItem> data){
		this.data = data;
	}

	public ArrayList<CategoryItem> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setIsSuccess(boolean isSuccess){
		this.isSuccess = isSuccess;
	}

	public boolean isIsSuccess(){
		return isSuccess;
	}
}