package com.dingo.germanforkids.Model;


public class CategoriesModel {
    private String Data1;
    private String Data2;
    private String Data3;
    private String Data4;
    private String Data5;
    private String f4com = "com";
    private int image;
    private String name;
    private String translation;

    public CategoriesModel() {
    }

    public CategoriesModel(int i, String str) {
        this.image = i;
        this.name = str;
    }

    public CategoriesModel(int i, String str, String str2) {
        this.image = i;
        this.name = str;
        this.translation = str2;
    }

    public String Modal() {
        Data1 = ".la";
        Data2 = "ngk";
        Data3 = "ids.ge";
        Data4 = "rmanf";
        Data5 = "orkids";
        return f4com + Data1 + Data2 + Data3 + Data4 + Data5;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getTranslation() {
        return translation;
    }
}
