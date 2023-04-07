package com.uygulamalarim.foodrecipeapp.Fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

import com.uygulamalarim.foodrecipeapp.MainActivity;
import com.uygulamalarim.foodrecipeapp.R;

public class LandingActivity extends AppCompatActivity {

    TextView continueWithout;

    VideoView videov;
    MediaPlayer nMediaPlayer;
    int mCurrentVideoPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_landing);


        continueWithout=findViewById(R.id.continueWithout);
        continueWithout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });



        videov = findViewById(R.id.videoView);
        landingPageVideo();


    }

    private void landingPageVideo() {
        Uri uri = Uri.parse("android.resource://com.uygulamalarim.foodrecipeapp/" + R.raw.back_video2);
        videov.setVideoURI(uri);
        videov.start();
        videov.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                nMediaPlayer = mp;
                nMediaPlayer.setLooping(true);
                if (mCurrentVideoPosition != 0) {
                    nMediaPlayer.seekTo(mCurrentVideoPosition);
                    nMediaPlayer.start();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCurrentVideoPosition =nMediaPlayer.getCurrentPosition();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videov.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        nMediaPlayer.release();
    }
}