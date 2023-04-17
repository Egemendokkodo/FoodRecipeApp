package com.uygulamalarim.foodrecipeapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uygulamalarim.foodrecipeapp.Adapter.CategoryAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;

import com.uygulamalarim.foodrecipeapp.Adapter.SearchRecyclerAdapter;
import com.uygulamalarim.foodrecipeapp.Model.CategoryModel.CategoryDomain;
import com.uygulamalarim.foodrecipeapp.Model.RandomApiModel.RandomApiMain;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiCalls;
import com.uygulamalarim.foodrecipeapp.util.UserData;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements RandomAdapter.OnClickListenerRecycler,CategoryAdapter.CategorySelectListener,SearchRecyclerAdapter.onClickListenerRecyclerSearch {
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

    @SuppressLint("SetTextI18n")
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
            UserData.getInstance().setUsername(username);
        }else{
            did_login=0;
            helloText.setText("Hello,");
        }


        initView(view);


            new ApiCalls()
                    .searchFunctionality(getContext(),
                            searchRecipeHome,
                            searchRecyclerHome,
                            searchRecyclerList,
                            searchRecyclerAdapter);
            new ApiCalls().
                    makeRandomRecycler(getContext(),
                            randomRecipeList,
                            randomAdapter);


        }

        private void initView(View view) {
            recyclerViewRandom = view.findViewById(R.id.recommendationRecycler);
            randomAdapter = new RandomAdapter(randomRecipeList,this);
            recyclerViewRandom.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerViewRandom.setAdapter(randomAdapter);

            searchRecipeHome=view.findViewById(R.id.searchRecipeHome);
            searchRecyclerHome=view.findViewById(R.id.searchRecyclerHome);
            searchRecyclerAdapter=new SearchRecyclerAdapter(searchRecyclerList,this);//this kısmı onclick recycler olacak
            searchRecyclerHome.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
            searchRecyclerHome.setAdapter(searchRecyclerAdapter);
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
    @Override
    public void onClickRecyclerSearch(int position) {
        String food_name=searchRecyclerList.get(position).getResults().get(position).getTitle();
        Intent i=new Intent(getContext(),DetailedPage.class);
        i.putExtra("FOOD_NAME",food_name);
        startActivity(i);

    }


}