package com.uygulamalarim.foodrecipeapp.Model.FirebaseResponseModel;

public class FirebaseModel {

    public String name;
    public String url;
    public FirebaseModel(String recipeName, String recipePic) {
        this.name = recipeName;
        this.url = recipePic;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
