package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uygulamalarim.foodrecipeapp.MainActivity;
import com.uygulamalarim.foodrecipeapp.R;
import com.uygulamalarim.foodrecipeapp.util.FirebaseAuth;
import com.uygulamalarim.foodrecipeapp.util.User;

import java.util.Objects;

public class LoginPage extends AppCompatActivity {

    ImageButton backBtn;
    Button loginbtn;
    TextView createNewOneBtn;

    TextInputEditText loginUsername,loginpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        backBtn=findViewById(R.id.backbtn);
        loginbtn=findViewById(R.id.loginbtn);
        createNewOneBtn=findViewById(R.id.createNewOneBtn);
        loginUsername=findViewById(R.id.loginUsername);
        loginpassword=findViewById(R.id.loginPassword);

        Intent intent = getIntent();
        String email = intent.getStringExtra("username");
        if(email!=null){
            loginUsername.setText(email.toString());
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginUsername.getText().toString().isEmpty()||loginpassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "Please input all the fields.", Toast.LENGTH_SHORT).show();
                }else{
                    String username=loginUsername.getText().toString().trim();
                    String password=loginpassword.getText().toString().trim();
                    new FirebaseAuth().loginToDB(username,password,view,LoginPage.this,getApplicationContext());
                }
            }
        });

        createNewOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SignUpPage.class);
                startActivity(i);
                finish();
            }
        });



    }

}