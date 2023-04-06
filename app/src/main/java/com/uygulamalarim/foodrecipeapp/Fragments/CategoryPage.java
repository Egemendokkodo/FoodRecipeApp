package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.uygulamalarim.foodrecipeapp.Adapter.CategoryAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.CategoryRecycler;
import com.uygulamalarim.foodrecipeapp.Adapter.InstructionsAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;
import com.uygulamalarim.foodrecipeapp.Model.CategoryRecyclerModel.CategoryRecyclerModel;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryPage extends AppCompatActivity implements CategoryRecycler.OnItemClickListener {

    private RecyclerView recyclerViewCategory;
    private CategoryRecycler categoryAdapter;
    private ArrayList<CategoryRecyclerModel> categoryList = new ArrayList<CategoryRecyclerModel>();
    ImageButton backBtnCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_page);

        Intent intent = getIntent();
        String category_name = intent.getStringExtra("CATEGORY_NAME");
        Log.d("CATEGORY NAME: ",category_name.toLowerCase(Locale.ROOT));

        backBtnCategory=findViewById(R.id.backBtnCategory);
        backBtnCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerViewCategory = findViewById(R.id.recyclerViewCategory);
        categoryAdapter = new CategoryRecycler(categoryList,this);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerViewCategory.setAdapter(categoryAdapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<CategoryRecyclerModel> call = service.getFoodsByCategoryName(
                "257f8904a2aa42dcad6a8d48f6a4d89b",
                category_name.toLowerCase(Locale.ROOT),
                5
        );

        call.enqueue(new Callback<CategoryRecyclerModel>() {
            @Override
            public void onResponse(Call<CategoryRecyclerModel> call, Response<CategoryRecyclerModel> response) {
                if (response.isSuccessful()) {
                    categoryList.clear();
                    CategoryRecyclerModel randomApiMain = response.body();
                    for(int i=0;i<5;i++){
                        categoryList.add(randomApiMain);
                    }

                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CategoryRecyclerModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        String food_name=categoryList.get(position).getResults().get(position).getTitle();
        //Toast.makeText(getContext(), randomRecipeList.get(position).getRecipes().get(position).getTitle(), Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,DetailedPage.class);
        i.putExtra("FOOD_NAME",food_name);
        startActivity(i);
    }
}