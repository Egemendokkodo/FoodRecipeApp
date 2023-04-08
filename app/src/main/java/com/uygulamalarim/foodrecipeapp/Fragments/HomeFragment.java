package com.uygulamalarim.foodrecipeapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uygulamalarim.foodrecipeapp.Adapter.CategoryAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;

import com.uygulamalarim.foodrecipeapp.Adapter.SearchRecyclerAdapter;
import com.uygulamalarim.foodrecipeapp.Model.CategoryModel.CategoryDomain;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
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


public class HomeFragment extends Fragment implements RandomAdapter.OnClickListenerRecycler,CategoryAdapter.CategorySelectListener {
    private RecyclerView recyclerViewCategory;
    private RecyclerView.Adapter adapter;

    private RecyclerView recyclerViewRandom;
    private RandomAdapter randomAdapter;
    private List<RandomApiMain> randomRecipeList = new ArrayList<RandomApiMain>();
    public Integer did_login=0;

    private androidx.appcompat.widget.SearchView searchRecipeHome;
    private RecyclerView searchRecyclerHome;
    private RecyclerView.Adapter searchRecyclerAdapter;
    private List<SearchModelMain> searchRecyclerList=new ArrayList<>();

    ArrayList<CategoryDomain> category=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerViewCategory(view);
        TextView helloText=view.findViewById(R.id.helloText);

        Intent intent = getActivity().getIntent();
        String username = intent.getStringExtra("username");
        if (username!=null){
            did_login=1;
            helloText.setText("Hello, "+username);
        }else{
            did_login=0;
            helloText.setText("Hello,");
        }


        initView(view);
        makeRandomRecycler();



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
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://api.spoonacular.com/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    ApiInterface service = retrofit.create(ApiInterface.class);
                    Call<SearchModelMain> call = service.getFoodsBySearch(
                            "f4b1cd31d74f45e2b60905a483988e20",
                            query

                    );
                    call.enqueue(new Callback<SearchModelMain>() {
                        @Override
                        public void onResponse(Call<SearchModelMain> call, Response<SearchModelMain> response) {
                            if (response.isSuccessful()) {
                                SearchModelMain randomApiMain = response.body();
                                searchRecyclerList.clear();
                                for (int i = 0; i <randomApiMain.getNumber() ; i++) {
                                    searchRecyclerList.add(randomApiMain);
                                }


                                searchRecyclerAdapter.notifyDataSetChanged();
                            }
                        }

                        @Override
                        public void onFailure(Call<SearchModelMain> call, Throwable t) {
                            Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                return false;
            }
        });

    }

    private void initView(View view) {
        recyclerViewRandom = view.findViewById(R.id.recommendationRecycler);
        randomAdapter = new RandomAdapter(randomRecipeList,this);
        recyclerViewRandom.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerViewRandom.setAdapter(randomAdapter);

        searchRecipeHome=view.findViewById(R.id.searchRecipeHome);
        searchRecyclerHome=view.findViewById(R.id.searchRecyclerHome);
        searchRecyclerAdapter=new SearchRecyclerAdapter(searchRecyclerList);//this kısmı onclick recycler olacak
        searchRecyclerHome.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        searchRecyclerHome.setAdapter(searchRecyclerAdapter);
    }

    private void makeRandomRecycler() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<RandomApiMain> call = service.getRandomRecipes(
                "f4b1cd31d74f45e2b60905a483988e20",
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



        category.add(new CategoryDomain("Main Course","pic1"));
        category.add(new CategoryDomain("Salad","pic2"));
        category.add(new CategoryDomain("Dessert","pic3"));
        category.add(new CategoryDomain("Drink","pic4"));
        category.add(new CategoryDomain("Appetizer","pic5"));
        adapter=new CategoryAdapter(category,this);
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
    @Override
    public void onItemClicked(int position) {
        String food_category=category.get(position).getTitle();
        Intent i=new Intent(getContext(),CategoryPage.class);
        i.putExtra("CATEGORY_NAME",food_category);
        startActivity(i);

    }


}