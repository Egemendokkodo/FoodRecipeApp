package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.uygulamalarim.foodrecipeapp.Adapter.InstructionsAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.RecipeModelMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiCalls;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiInterface;
import com.uygulamalarim.foodrecipeapp.util.MyDrawables;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailedPage extends AppCompatActivity {
    private List<RecipeModelMain> randomRecipeList = new ArrayList<RecipeModelMain>();
    TextView sourcename,minutes,calories,ischeap,isglutenfree,isvegetarian,isvegan,description;
    TextView isCheaptext,isGlutenFreetext,isVegetariantext,isVegantext;
    RoundedImageView foodpic;
    ImageButton backbtn;

    private RecyclerView instructionsRecycler;
    private InstructionsAdapter instructionsAdapter;
    private List<RecipeModelMain> instructionsList = new ArrayList<RecipeModelMain>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);
        Intent intent = getIntent();
        String food_name = intent.getStringExtra("FOOD_NAME");
        TextView foodname=findViewById(R.id.food_name);



        initView();

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        new ApiCalls().makeDetailedPageApiCall(getApplicationContext(),food_name,randomRecipeList,instructionsList,foodname,sourcename,minutes,description,calories,foodpic,ischeap,isCheaptext,isglutenfree,isGlutenFreetext,isvegetarian,isVegetariantext,isvegan,isVegantext);


    }

    private void initView() {
        sourcename=findViewById(R.id.sourceName);
        minutes=findViewById(R.id.minutes);
        calories=findViewById(R.id.calories);
        ischeap=findViewById(R.id.isCheap);
        isglutenfree=findViewById(R.id.isGlutenFree);
        isvegetarian=findViewById(R.id.isVegetarian);
        isvegan=findViewById(R.id.isVegan);
        description=findViewById(R.id.description);
        foodpic=findViewById(R.id.foodpic);
        isCheaptext=findViewById(R.id.isCheaptext);
        isGlutenFreetext=findViewById(R.id.isGlutenFreetext);
        isVegetariantext=findViewById(R.id.isVegetariantext);
        isVegantext=findViewById(R.id.isVegantext);
        backbtn=findViewById(R.id.backbtn);

        instructionsRecycler = findViewById(R.id.instructionsRecycler);
        instructionsAdapter = new InstructionsAdapter(instructionsList);
        instructionsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        instructionsRecycler.setAdapter(instructionsAdapter);
    }

}