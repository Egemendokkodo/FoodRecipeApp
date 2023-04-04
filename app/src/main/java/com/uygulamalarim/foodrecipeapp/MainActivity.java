package com.uygulamalarim.foodrecipeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uygulamalarim.foodrecipeapp.Fragments.HomeFragment;
import com.uygulamalarim.foodrecipeapp.Model.MainModel;

import java.io.IOException;
import java.util.ArrayList;

import kotlinx.coroutines.GlobalScope;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    ArrayList<MainModel> list=new ArrayList<MainModel>();

    private static final String API_KEY = "OJSordFi1bhz2vKkKY4gFUtIBvqWlIrf";
    private static final String BASE_URL = "https://api.apilayer.com/spoonacular/recipes/complexSearch";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new HomeFragment())
                .commit();

    }


}