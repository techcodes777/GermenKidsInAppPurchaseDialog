package com.adsnativetamplete.retrofit.models;

import com.google.gson.annotations.SerializedName;

public class CategoryReq{

	@SerializedName("categoryName")
	private String categoryName;

	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}

	public String getCategoryName(){
		return categoryName;
	}
}