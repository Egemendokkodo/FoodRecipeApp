package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

public class WeightPerServing {
    private long amount;
    private Unit unit;

    public long getAmount() {
        return amount;
    }

    public void setAmount(long value) {
        this.amount = value;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit value) {
        this.unit = value;
    }
}
