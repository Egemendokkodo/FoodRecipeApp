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





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);
        Call<RecipeModelMain> call = service.getFullRecipe(
                "54dff7b37e4a49ac8585d04bb5e3ddaa",
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
                    Glide.with(getApplicationContext()).load(randomApiMain.getResults().get(0).getImage()).into(foodpic);
                    if (is_cheap){
                        ischeap.setCompoundDrawables(new MyDrawables(getApplicationContext()).getCheapTrueDrawable(),null,null,null);
                        isCheaptext.setTextColor(Color.GREEN);
                    }
                    if (is_gluten_free){
                        isglutenfree.setCompoundDrawables(new MyDrawables(getApplicationContext()).getGlutenFreeTrueDrawable(),null,null,null);
                        isGlutenFreetext.setTextColor(Color.GREEN);
                    }
                    if (is_vegetarian){
                        isvegetarian.setCompoundDrawables(new MyDrawables(getApplicationContext()).getVegetarianTrueDrawable(),null,null,null);
                        isVegetariantext.setTextColor(Color.GREEN);
                    }
                    if (is_vegan){
                        isvegan.setCompoundDrawables(new MyDrawables(getApplicationContext()).getVeganTrueDrawable(),null,null,null);
                        isVegantext.setTextColor(Color.GREEN);
                    }






                }
            }

            @Override
            public void onFailure(Call<RecipeModelMain> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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