package com.uygulamalarim.foodrecipeapp.Model.RandomApiModel;

import java.util.List;

public class ExtendedIngredient {
    private String originalName;
    private String image;
    private double amount;
    private String unit;
    private Measures measures;
    private String nameClean;
    private String original;
    private List<String> meta;
    private String name;
    private long id;
    private String aisle;
    private Consistency consistency;

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String value) {
        this.originalName = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String value) {
        this.image = value;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String value) {
        this.unit = value;
    }

    public Measures getMeasures() {
        return measures;
    }

    public void setMeasures(Measures value) {
        this.measures = value;
    }

    public String getNameClean() {
        return nameClean;
    }

    public void setNameClean(String value) {
        this.nameClean = value;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String value) {
        this.original = value;
    }

    public List<String> getMeta() {
        return meta;
    }

    public void setMeta(List<String> value) {
        this.meta = value;
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

    public String getAisle() {
        return aisle;
    }

    public void setAisle(String value) {
        this.aisle = value;
    }

    public Consistency getConsistency() {
        return consistency;
    }

    public void setConsistency(Consistency value) {
        this.consistency = value;
    }
}
