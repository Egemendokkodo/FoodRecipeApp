package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

public class CaloricBreakdown {
    private double percentCarbs;
    private double percentProtein;
    private double percentFat;

    public double getPercentCarbs() {
        return percentCarbs;
    }

    public void setPercentCarbs(double value) {
        this.percentCarbs = value;
    }

    public double getPercentProtein() {
        return percentProtein;
    }

    public void setPercentProtein(double value) {
        this.percentProtein = value;
    }

    public double getPercentFat() {
        return percentFat;
    }

    public void setPercentFat(double value) {
        this.percentFat = value;
    }
}
