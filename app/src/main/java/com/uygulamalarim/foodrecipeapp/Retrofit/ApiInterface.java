package com.uygulamalarim.foodrecipeapp.Retrofit;

import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.RecipeModelMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("recipes/random")
    Call<RandomApiMain> getRandomRecipes(@Query("apiKey") String apiKey, @Query("tags") String tags, @Query("number") int number);
    @GET("recipes/complexSearch")
    Call<RecipeModelMain> getFullRecipe(@Query("apiKey") String apiKey, @Query("query") String query, @Query("addRecipeInformation") Boolean addRecipeInformation,@Query("addRecipeNutrition") Boolean addRecipeNutrition);
}