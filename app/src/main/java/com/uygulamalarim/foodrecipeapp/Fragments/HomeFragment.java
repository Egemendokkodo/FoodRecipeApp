package com.uygulamalarim.foodrecipeapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.uygulamalarim.foodrecipeapp.Adapter.CategoryAdapter;
import com.uygulamalarim.foodrecipeapp.Model.CategoryModel.CategoryDomain;
import com.uygulamalarim.foodrecipeapp.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewCategory;
    private RecyclerView.Adapter adapter;


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

    }
    private void recyclerViewCategory(View view) {
        // this is categorylist in the main page
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


}