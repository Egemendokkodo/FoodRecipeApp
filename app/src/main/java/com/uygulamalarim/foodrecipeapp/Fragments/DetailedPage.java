package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.uygulamalarim.foodrecipeapp.Adapter.InstructionsAdapter;
import com.uygulamalarim.foodrecipeapp.Model.RecipeModel.RecipeModelMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiCalls;

import java.util.ArrayList;
import java.util.List;

public class DetailedPage extends AppCompatActivity {
    private List<RecipeModelMain> randomRecipeList = new ArrayList<RecipeModelMain>();
    TextView sourcename,minutes,calories,ischeap,isglutenfree,isvegetarian,isvegan,description;
    TextView isCheaptext,isGlutenFreetext,isVegetariantext,isVegantext;
    RoundedImageView foodpic;
    ImageButton backbtn;
    ImageButton savebtn;

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


        new ApiCalls().makeDetailedPageApiCall(getApplicationContext(),food_name,randomRecipeList,instructionsList,foodname,sourcename,minutes,description,calories,foodpic,ischeap,isCheaptext,isglutenfree,isGlutenFreetext,isvegetarian,isVegetariantext,isvegan,isVegantext,savebtn);



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
        savebtn=findViewById(R.id.savebtn);

        instructionsRecycler = findViewById(R.id.instructionsRecycler);
        instructionsAdapter = new InstructionsAdapter(instructionsList);
        instructionsRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        instructionsRecycler.setAdapter(instructionsAdapter);
    }

}