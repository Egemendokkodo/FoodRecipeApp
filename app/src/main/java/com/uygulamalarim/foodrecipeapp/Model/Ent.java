package com.uygulamalarim.foodrecipeapp.Model;

public class Ent {
    private String image;
    private String localizedName;
    private String name;
    private long id;
    private Length temperature;

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String value) {
        this.localizedName = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }

    public Length getTemperature() {
        return temperature;
    }

    public void setTemperature(Length value) {
        this.temperature = value;
    }
}
