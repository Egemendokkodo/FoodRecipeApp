package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.uygulamalarim.foodrecipeapp.R;

public class DetailedPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_page);
        Intent intent = getIntent();
        String food_name = intent.getStringExtra("FOOD_NAME");
        Toast.makeText(this, food_name, Toast.LENGTH_SHORT).show();
        TextView foodname=findViewById(R.id.food_name);
        foodname.setText(food_name);
        /*MyBottomSheetFragment bottomSheetFragment=new MyBottomSheetFragment(food_name);
        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());*/
    }
}