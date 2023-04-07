package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.FirebaseAuth;

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

                new FirebaseAuth().saveToDb(view,getApplicationContext(),SignUpPage.this,username,email,password,repeatpassword,enterUsername,enterEmail);





            }
        });




    }


}