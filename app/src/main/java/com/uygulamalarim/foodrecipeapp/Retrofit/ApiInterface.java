package com.uygulamalarim.foodrecipeapp.Retrofit;

import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("recipes/random")
    Call<RandomApiMain> getRandomRecipes(@Query("apiKey") String apiKey, @Query("tags") String tags, @Query("number") int number);
}