package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

public class Flavonoid {
    private double amount;
    private Unit unit;
    private String name;
    private Double percentOfDailyNeeds;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit value) {
        this.unit = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Double getPercentOfDailyNeeds() {
        return percentOfDailyNeeds;
    }

    public void setPercentOfDailyNeeds(Double value) {
        this.percentOfDailyNeeds = value;
    }
}
