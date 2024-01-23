package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LogRegScreen.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
           startActivity(new Intent(SplashScreen.this, LogRegScreen.class));
        }
    }
}