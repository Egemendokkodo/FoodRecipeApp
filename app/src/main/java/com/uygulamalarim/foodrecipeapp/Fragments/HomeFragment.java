package com.uygulamalarim.foodrecipeapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.uygulamalarim.foodrecipeapp.Adapter.CategoryAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;

import com.uygulamalarim.foodrecipeapp.Model.CategoryModel.CategoryDomain;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment implements RandomAdapter.OnClickListenerRecycler {
    private RecyclerView recyclerViewCategory;
    private RecyclerView.Adapter adapter;

    private RecyclerView recyclerViewRandom;
    private RandomAdapter randomAdapter;
    private List<RandomApiMain> randomRecipeList = new ArrayList<RandomApiMain>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewCategory(view);

        recyclerViewRandom = view.findViewById(R.id.recommendationRecycler);
        randomAdapter = new RandomAdapter(randomRecipeList,this);
        recyclerViewRandom.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewRandom.setAdapter(randomAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<RandomApiMain> call = service.getRandomRecipes(
                "d476d45617e540db9c0d6dcd8cd02a1c",
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
                    Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });












    }
    private void recyclerViewCategory(View view) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewCategory=view.findViewById(R.id.categoriesRecycler);
        recyclerViewCategory.setLayoutManager(linearLayoutManager);


        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Main Dishes","pic1"));
        category.add(new CategoryDomain("Salads","pic2"));
        category.add(new CategoryDomain("Desserts","pic3"));
        category.add(new CategoryDomain("Drinks","pic4"));
        category.add(new CategoryDomain("Appetiser","pic5"));
        adapter=new CategoryAdapter(category);
        recyclerViewCategory.setAdapter(adapter);
    }


    @Override
    public void onClickRecycler(int position) {
        String food_name=randomRecipeList.get(position).getRecipes().get(position).getTitle();
        //Toast.makeText(getContext(), randomRecipeList.get(position).getRecipes().get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent(getContext(),DetailedPage.class);
        i.putExtra("FOOD_NAME",food_name);
        startActivity(i);

    }


}