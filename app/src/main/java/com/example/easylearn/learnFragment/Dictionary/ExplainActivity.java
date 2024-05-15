package com.example.easylearn.learnFragment.Dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.easylearn.Authorization;
import com.example.easylearn.R;
import com.example.easylearn.Registration;

public class ExplainActivity extends AppCompatActivity {
    TextView textView;
    ImageButton backDictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_explain);

        textView = findViewById(R.id.text_explain);
        String expainText = getIntent().getStringExtra("explanation");
        textView.setText(expainText);

        backDictionary = findViewById(R.id.back_dictionary);
        backDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}