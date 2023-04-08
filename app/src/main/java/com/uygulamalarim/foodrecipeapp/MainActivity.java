package com.uygulamalarim.foodrecipeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.uygulamalarim.foodrecipeapp.Fragments.HomeFragment;
import com.uygulamalarim.foodrecipeapp.Fragments.ProfileFragment;
import com.uygulamalarim.foodrecipeapp.Fragments.SavedFragment;
import com.uygulamalarim.foodrecipeapp.Fragments.SearchFragment;
import com.uygulamalarim.foodrecipeapp.util.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomnavbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnavbar=findViewById(R.id.bottomNavbar);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new HomeFragment())
                .commit();


        bottomnavbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        changeFragment(new HomeFragment());
                        break;

                    case R.id.search:
                        changeFragment(new SearchFragment());
                        break;
                    case R.id.saved:
                        changeFragment(new SavedFragment());
                        break;
                    case R.id.profilepage:

                        changeFragment(new ProfileFragment());

                        break;

                }
                return true;
            }


        });

    }
    public void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit();
    }


}