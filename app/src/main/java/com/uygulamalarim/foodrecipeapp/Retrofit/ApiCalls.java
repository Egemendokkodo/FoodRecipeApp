package com.uygulamalarim.foodrecipeapp.Retrofit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.RecipeModelMain;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.util.FirebaseAuth;
import com.uygulamalarim.foodrecipeapp.util.MyDrawables;
import com.uygulamalarim.foodrecipeapp.util.UserData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCalls {

    private final String BASE_URL="https://api.spoonacular.com/";
    private final String API="966347ce995847869022a2e31b4d08c0";

    private final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public void searchFunctionality(Context context, SearchView searchRecipeHome, RecyclerView searchRecyclerHome, List<SearchModelMain> searchRecyclerList, RecyclerView.Adapter searchRecyclerAdapter){


        searchRecipeHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (query.isEmpty()){
                    searchRecyclerHome.setVisibility(View.GONE);
                }else{
                    searchRecyclerHome.setVisibility(View.VISIBLE);

                    ApiInterface service = retrofit.create(ApiInterface.class);
                    Call<SearchModelMain> call = service.getFoodsBySearch(
                            API,
                            query

                    );
                    call.enqueue(new Callback<SearchModelMain>() {
                        @Override
                        public void onResponse(Call<SearchModelMain> call, Response<SearchModelMain> response) {
                            if (query.isEmpty()){
                                searchRecyclerHome.setVisibility(View.GONE);
                            }else{

                                if (response.isSuccessful()) {
                                    SearchModelMain randomApiMain = response.body();
                                    if (randomApiMain.getTotalResults() == 0) {
                                        searchRecyclerHome.setVisibility(View.GONE);
                                    } else {
                                        searchRecyclerHome.setVisibility(View.VISIBLE);
                                        searchRecyclerList.clear();
                                        for (int i = 0; i < randomApiMain.getNumber(); i++) {
                                            searchRecyclerList.add(randomApiMain);
                                        }
                                        searchRecyclerAdapter.notifyDataSetChanged();
                                    }
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<SearchModelMain> call, Throwable t) {
                            Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return false;
            }
        });
    }
    public void makeRandomRecycler(Context context,List<RandomApiMain> randomRecipeList,RecyclerView.Adapter randomAdapter){

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<RandomApiMain> call = service.getRandomRecipes(
                API,
                "main dish",
                5
        );

        call.enqueue(new Callback<RandomApiMain>() {
            @Override
            public void onResponse(Call<RandomApiMain> call, Response<RandomApiMain> response) {
                if (response.isSuccessful()) {
                    RandomApiMain randomApiMain = response.body();
                    randomRecipeList.clear();
                    for (int i = 0; i < 5; i++) {
                        randomRecipeList.add(randomApiMain);
                    }
                    randomAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<RandomApiMain> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void makeDetailedPageApiCall(Context context, String food_name, List<RecipeModelMain> randomRecipeList, List<RecipeModelMain> instructionsList, TextView foodname, TextView sourcename, TextView minutes, TextView description, TextView calories, RoundedImageView foodpic, TextView ischeap, TextView isCheaptext, TextView isglutenfree, TextView isGlutenFreetext, TextView isvegetarian, TextView isVegetariantext, TextView isvegan, TextView isVegantext, ImageButton savedbtn){
        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<RecipeModelMain> call = service.getFullRecipe(
                API,
                food_name,
                true,
                true
        );

        call.enqueue(new Callback<RecipeModelMain>() {
            @Override
            public void onResponse(Call<RecipeModelMain> call, Response<RecipeModelMain> response) {
                if (response.isSuccessful()) {
                    RecipeModelMain randomApiMain = response.body();
                    randomRecipeList.clear();



                    String ready_in_minutes= String.valueOf(randomApiMain.getResults().get(0).getReadyInMinutes());
                    String source_name="By "+randomApiMain.getResults().get(0).getSourceName();
                    boolean is_cheap=randomApiMain.getResults().get(0).getCheap();
                    boolean is_gluten_free=randomApiMain.getResults().get(0).getGlutenFree();
                    boolean is_vegetarian=randomApiMain.getResults().get(0).getVegetarian();
                    boolean is_vegan=randomApiMain.getResults().get(0).getVegan();
                    String _calories= String.valueOf(randomApiMain.getResults().get(0).getNutrition().getNutrients().get(0).getAmount());
                    String _desc=randomApiMain.getResults().get(0).getSummary().toString();


                    for (int i = 1; i <= randomApiMain.getResults().get(0).getAnalyzedInstructions().get(0).getSteps().size(); i++) {
                        instructionsList.add(randomApiMain);
                    }



                    foodname.setText(food_name);
                    sourcename.setText(source_name);
                    minutes.setText("  "+ready_in_minutes+" mins.");
                    description.setText(_desc);
                    calories.setText("  "+_calories+" kCal");
                    Glide.with(context).load(randomApiMain.getResults().get(0).getImage()).into(foodpic);
                    if (is_cheap){
                        ischeap.setCompoundDrawables(new MyDrawables(context).getCheapTrueDrawable(),null,null,null);
                        isCheaptext.setTextColor(Color.GREEN);
                    }
                    if (is_gluten_free){
                        isglutenfree.setCompoundDrawables(new MyDrawables(context).getGlutenFreeTrueDrawable(),null,null,null);
                        isGlutenFreetext.setTextColor(Color.GREEN);
                    }
                    if (is_vegetarian){
                        isvegetarian.setCompoundDrawables(new MyDrawables(context).getVegetarianTrueDrawable(),null,null,null);
                        isVegetariantext.setTextColor(Color.GREEN);
                    }
                    if (is_vegan){
                        isvegan.setCompoundDrawables(new MyDrawables(context).getVeganTrueDrawable(),null,null,null);
                        isVegantext.setTextColor(Color.GREEN);
                    }


                    savedbtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {


                            if(!UserData.getInstance().getUsername().toString().equals("")){
                                new FirebaseAuth().saveRecipe(UserData.getInstance().getUsername().toString(),food_name.toString(),randomApiMain.getResults().get(0).getImage());
                                new FirebaseAuth().showSnackbar(view,"Recipe Saved Successfully");
                            }else{
                                new FirebaseAuth().showSnackbar(view,"You must login.");
                            }
                        }
                    });




                }
            }

            @Override
            public void onFailure(Call<RecipeModelMain> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
    public void makeGridRecycler(Context context,List<SearchModelMain> gridList, RecyclerView.Adapter gridAdapter) {

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<SearchModelMain> call = service.getForGridRecycler(API, "main dish", 51);

        call.enqueue(new Callback<SearchModelMain>() {
            @Override
            public void onResponse(Call<SearchModelMain> call, Response<SearchModelMain> response) {
                if (response.isSuccessful()) {
                    SearchModelMain randomApiMain = response.body();
                    gridList.clear();
                    for (int i = 0; i < randomApiMain.getNumber(); i++) {
                        gridList.add(randomApiMain);
                    }

                    gridAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<SearchModelMain> call, Throwable t) {
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
