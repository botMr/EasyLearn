package com.example.easylearn;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView aniVec = findViewById(R.id.splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                aniVec.setVisibility(View.VISIBLE);
                AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) aniVec.getDrawable();
                drawable.start();
            }
        }, 2000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(SplashScreen.this, Authorization.class));
            }
        }, 5000);
    }
}