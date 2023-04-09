package com.uygulamalarim.foodrecipeapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.uygulamalarim.foodrecipeapp.Adapter.GridAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;
import com.uygulamalarim.foodrecipeapp.Model.SearchModel.SearchModelMain;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.Retrofit.ApiCalls;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    RecyclerView gridRecycler;
    RecyclerView.Adapter gridAdapter;
    SearchView searchRecipeSearchFragment;
    List<SearchModelMain> gridList=new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchRecipeSearchFragment=view.findViewById(R.id.searchRecipeSearchFragment);







        gridRecycler=view.findViewById(R.id.gridRecycler);
        gridAdapter=new GridAdapter(getContext(),gridList);
        gridRecycler.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        );
        gridRecycler.setAdapter(gridAdapter);
        new ApiCalls().makeGridRecycler(getContext(),gridList,gridAdapter);



    }
}