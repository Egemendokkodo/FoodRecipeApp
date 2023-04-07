package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.AuthHelper;

public class SignUpPage extends AppCompatActivity {

    ImageButton backbtn2;
    Button signupbtn;
    TextInputEditText enterEmail,enterPassword,repeatPassword,enterUsername;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        signupbtn=findViewById(R.id.signupbtn);
        enterUsername=findViewById(R.id.enterUsername);
        enterEmail=findViewById(R.id.enterEmail);
        enterPassword=findViewById(R.id.enterPassword);
        repeatPassword=findViewById(R.id.repeatPassword);

        backbtn2=findViewById(R.id.backbtn2);
        backbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String username= enterUsername.getText().toString();
                String email= enterEmail.getText().toString();
                String password= enterPassword.getText().toString();
                String repeatpassword= repeatPassword.getText().toString();

                if (enterUsername.length()<=4||username.contains(".")||username.contains("#")||username.contains("$")||username.contains("[")||username.contains("]")){
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
                                            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || repeatpassword.isEmpty()) {
                                                showSnackbar(view,"Please input all the fields.");
                                            } else {
                                                if (!password.equals(repeatpassword)) {
                                                    showSnackbar(view,"Passwords does not match.");
                                                }
                                                if(!android.util.Patterns.EMAIL_ADDRESS.matcher(enterEmail.getText().toString()).matches()){
                                                    showSnackbar(view,"Email is invalid.");
                                                }
                                                else {
                                                    AuthHelper authHelper= new AuthHelper(username, email, password);
                                                    reference.child(username).setValue(authHelper);
                                                    Intent i= new Intent(SignUpPage.this, LoginPage.class);
                                                    i.putExtra("username", username);
                                                    startActivity(i);
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
        });




    }
    private void showSnackbar(View view,String warnMessage){
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

}