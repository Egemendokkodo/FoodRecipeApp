package com.uygulamalarim.foodrecipeapp.Model.RandomApiModel;

public class Metric {
    private double amount;
    private String unitShort;
    private String unitLong;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double value) {
        this.amount = value;
    }

    public String getUnitShort() {
        return unitShort;
    }

    public void setUnitShort(String value) {
        this.unitShort = value;
    }

    public String getUnitLong() {
        return unitLong;
    }

    public void setUnitLong(String value) {
        this.unitLong = value;
    }
}
