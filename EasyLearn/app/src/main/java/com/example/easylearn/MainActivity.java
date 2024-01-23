package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ai(View view) {
        startActivity(new Intent(MainActivity.this,AiScreen.class));
    }

    public void ai_assistant_bar(View view) {
        startActivity(new Intent(MainActivity.this,AiScreen.class));
    }
}