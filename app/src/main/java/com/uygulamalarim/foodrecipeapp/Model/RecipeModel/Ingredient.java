package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

import java.util.List;

public class Ingredient {
    private double amount;
    private String unit;
    private String name;
    private long id;
    private List<Flavonoid> nutrients;

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

    public List<Flavonoid> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Flavonoid> value) {
        this.nutrients = value;
    }
}
