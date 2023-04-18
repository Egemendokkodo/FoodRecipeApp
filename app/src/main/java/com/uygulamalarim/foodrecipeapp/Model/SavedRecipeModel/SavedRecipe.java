package com.uygulamalarim.foodrecipeapp.Model.SavedRecipeModel;

public class SavedRecipe {
    String recipe_name,recipe_url;

    public SavedRecipe(String recipe_name,String recipe_url){
        this.recipe_name=recipe_name;
        this.recipe_url=recipe_url;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_url() {
        return recipe_url;
    }

    public void setRecipe_url(String recipe_url) {
        this.recipe_url = recipe_url;
    }
}
