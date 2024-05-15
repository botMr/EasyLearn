package com.example.easylearn.libraryFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.HideSystemUI;
import com.example.easylearn.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BookActivity extends AppCompatActivity {
    FloatingActionButton text_settings;
    TextView textBook;
    View setBackgroundColor;
    SettingsDialogManager settingsDialogManager;
    ImageButton backBook_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book);
        HideSystemUI.hideSystemUI(this);

        text_settings = findViewById(R.id.text_settings);
        textBook = findViewById(R.id.textBook);
        setBackgroundColor = findViewById(R.id.mainBook);
        backBook_info = findViewById(R.id.backBook_info);
        settingsDialogManager = new SettingsDialogManager(this,textBook,setBackgroundColor);

        String bookTextUrl = getIntent().getStringExtra("bookText");
        ApiConnection.downloadFromUrl(bookTextUrl,textBook);
        backBook_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        text_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settingsDialogManager.showSettingsDialog();
            }
        });
  
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}