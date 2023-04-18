package com.uygulamalarim.foodrecipeapp.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.UserData;


public class ProfileFragment extends Fragment {

    Button directToLogin,logoutBtn;
    TextView usernameTv,mailTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(UserData.getInstance().getUsername().toString().equals("")){
            return inflater.inflate(R.layout.fragment_profile_you_must_login, container, false);
        }else{
            return inflater.inflate(R.layout.fragment_profile, container, false);
        }

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (!UserData.getInstance().getUsername().toString().equals("")){
            logoutBtn=view.findViewById(R.id.logoutBtn);
            mailTv=view.findViewById(R.id.mailTv);
            usernameTv=view.findViewById(R.id.usernameTv);
            String username=UserData.getInstance().getUsername().toString();
            logoutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(),LoginPage.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            usernameTv.setText(username);
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("users");
            DatabaseReference savedRecipeRef = reference.child(username).child("email");

            savedRecipeRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    String email = dataSnapshot.getValue(String.class);
                    mailTv.setText(email.toString());
                }

                @Override
                public void onCancelled(DatabaseError error) {

                }
            });




        }else{
            directToLogin=view.findViewById(R.id.directToLogin);
            directToLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getContext(),LoginPage.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
        }


    }
}