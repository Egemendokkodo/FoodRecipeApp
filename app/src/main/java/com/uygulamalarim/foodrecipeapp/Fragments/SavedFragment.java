package com.uygulamalarim.foodrecipeapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uygulamalarim.foodrecipeapp.Adapter.RandomAdapter;
import com.uygulamalarim.foodrecipeapp.Adapter.SavedAdapter;
import com.uygulamalarim.foodrecipeapp.Model.FirebaseResponseModel.FirebaseModel;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.UserData;

import java.util.ArrayList;
import java.util.List;


public class SavedFragment extends Fragment {

    private RecyclerView recyclerviewSaved;
    private SavedAdapter savedAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        String username = UserData.getInstance().getUsername().toString();

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference("users");
        DatabaseReference savedRecipeRef=reference.child(username).child("SavedRecipes");


        List<FirebaseModel> recipeList = new ArrayList<>();
        savedAdapter = new SavedAdapter(recipeList);
        recyclerviewSaved = view.findViewById(R.id.savedRecycler);
        recyclerviewSaved.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerviewSaved.setAdapter(savedAdapter);

        ValueEventListener recipeListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot recipeSnapshot : snapshot.getChildren()) {
                    String recipeName = recipeSnapshot.child("recipe_name").getValue(String.class);
                    String recipePic = recipeSnapshot.child("recipe_url").getValue(String.class);
                    FirebaseModel recipe = new FirebaseModel(recipeName,recipePic);
                    recipeList.add(recipe);
                }
                savedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        savedRecipeRef.addListenerForSingleValueEvent(recipeListener);





    }
}