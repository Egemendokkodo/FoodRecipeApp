package com.uygulamalarim.foodrecipeapp.Model.RecipeModel;

import java.util.List;

public class Nutrition {
    private CaloricBreakdown caloricBreakdown;
    private WeightPerServing weightPerServing;
    private List<Ingredient> ingredients;
    private List<Flavonoid> flavonoids;
    private List<Flavonoid> properties;
    private List<Flavonoid> nutrients;

    public CaloricBreakdown getCaloricBreakdown() {
        return caloricBreakdown;
    }

    public void setCaloricBreakdown(CaloricBreakdown value) {
        this.caloricBreakdown = value;
    }

    public WeightPerServing getWeightPerServing() {
        return weightPerServing;
    }

    public void setWeightPerServing(WeightPerServing value) {
        this.weightPerServing = value;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> value) {
        this.ingredients = value;
    }

    public List<Flavonoid> getFlavonoids() {
        return flavonoids;
    }

    public void setFlavonoids(List<Flavonoid> value) {
        this.flavonoids = value;
    }

    public List<Flavonoid> getProperties() {
        return properties;
    }

    public void setProperties(List<Flavonoid> value) {
        this.properties = value;
    }

    public List<Flavonoid> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Flavonoid> value) {
        this.nutrients = value;
    }
}
