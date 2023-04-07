package com.uygulamalarim.foodrecipeapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uygulamalarim.foodrecipeapp.Fragments.LoginPage;
import com.uygulamalarim.foodrecipeapp.MainActivity;

public class FirebaseAuth {


    public void saveToDb(View view, Context context, Activity activity, String username, String email, String password, String repeatpassword, EditText enterUsername, EditText enterEmail){

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        DatabaseReference reference =database.getReference("users");

        if (username.length()<=4||username.contains(".")||username.contains("#")||username.contains("$")||username.contains("[")||username.contains("]")){
            showSnackbar(view,"Username must not contain '.', '#', '$', '[', or ']' and length must be >=4");

        }else{
            reference.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        enterUsername.setError("Username already exists.");
                    } else {
                        // check if email already exists
                        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    enterEmail.setError("Email already exists.");
                                } else {
                                    if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {
                                        showSnackbar(view,"Please input all the fields.");
                                    } else {
                                        if (!password.equals(repeatpassword)) {
                                            showSnackbar(view,"Passwords does not match.");
                                        } else if (email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {
                                            showSnackbar(view,"Please input all the fields.");
                                        } else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(enterEmail.getText().toString()).matches()){
                                            showSnackbar(view,"Email is invalid.");
                                        } else {
                                            AuthHelper authHelper= new AuthHelper(username, email, password);
                                            reference.child(username).setValue(authHelper);
                                            Intent i= new Intent(context, LoginPage.class);
                                            i.putExtra("username", username);
                                            activity.startActivity(i);
                                            activity.finish();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }





    private void showSnackbar(View view, String warnMessage){
        final Snackbar snackbar = Snackbar.make(view, warnMessage, Snackbar.LENGTH_SHORT);

        snackbar.show();

        new CountDownTimer(2000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                snackbar.dismiss();
            }
        }.start();

    }

    public void loginToDB(String username,String password,View view,Activity activity,Context context){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");


        usersRef.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            Intent i=new Intent(context, MainActivity.class);
                            activity.startActivity(i);
                            activity.finish();
                        } else {
                            showSnackbar(view,"Invalid credentials.");
                        }
                    }
                } else {
                    showSnackbar(view,"Invalid credentials.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Hata oluştu
            }
        });
    }


}
