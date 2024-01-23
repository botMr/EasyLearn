package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogRegScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg_screen);
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegScreen.class);
        startActivity(intent);
    }

    public void login(View view) {
        startActivity(new Intent(LogRegScreen.this, LogScreen.class));
    }
}