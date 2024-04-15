package com.example.easylearn;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomePage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    ImageButton backButton;

    HomeFragment homeFragment;
    GptFragment gptFragment;

    LibraryFragment libraryFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        homeFragment = new HomeFragment();
        gptFragment = new GptFragment();
        libraryFragment = new LibraryFragment();


        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                }
                if (menuItem.getItemId()==R.id.ai_assistant) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, gptFragment).commit();
                }
                if (menuItem.getItemId()==R.id.library) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, libraryFragment).commit();
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home);

    }
}