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
import com.uygulamalarim.foodrecipeapp.R;
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
                //validate username and password then firebase login

                if (loginUsername.getText().toString().isEmpty()||loginpassword.getText().toString().isEmpty()){
                    Toast.makeText(LoginPage.this, "Please input all the fields.", Toast.LENGTH_SHORT).show();
                }else{
                    checkUser(view);
                }
            }
        });

        createNewOneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),SignUpPage.class);
                startActivity(i);
            }
        });



    }

    public void checkUser(View view){
        String username=loginUsername.getText().toString().trim();
        String password=loginpassword.getText().toString().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("users");


        usersRef.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        User user = userSnapshot.getValue(User.class);
                        if (user.getPassword().equals(password)) {
                            Toast.makeText(LoginPage.this, "GİRİŞ BAŞARILI", Toast.LENGTH_SHORT).show();
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