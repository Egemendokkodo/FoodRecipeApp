package com.uygulamalarim.foodrecipeapp.Model.SearchModel;

public class Result {
    private String image;
    private long id;
    private String title;
    private ImageType imageType;

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public long getid() {
        return id;
    }

    public void setid(long value) {
        this.id = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String value) {
        this.title = value;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType value) {
        this.imageType = value;
    }
}
