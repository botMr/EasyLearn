package com.example.easylearn;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.accountFragment.AccountFragment;
import com.example.easylearn.learnFragment.LearnMaterilsFragment;
import com.example.easylearn.libraryFragment.LibraryFragment;
import com.example.easylearn.practiceFragment.PracticeFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class HomePage extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    LearnMaterilsFragment learnMaterilsFragment;
    PracticeFragment practiceFragment;
    LibraryFragment libraryFragment;
    AccountFragment accountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
        chipNavigationBar = findViewById(R.id.bottom_nav);
        HideSystemUI.hideSystemUI(this);


        learnMaterilsFragment = new LearnMaterilsFragment();
        practiceFragment = new PracticeFragment();
        libraryFragment = new LibraryFragment();
        accountFragment = new AccountFragment();

       chipNavigationBar.setOnItemSelectedListener((ChipNavigationBar.OnItemSelectedListener) menuItem -> {
           if (menuItem==R.id.nav_home){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, learnMaterilsFragment).commit();
           } else if (menuItem==R.id.nav_practic){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, practiceFragment).commit();

           } else if (menuItem == R.id.nav_library) {
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, libraryFragment).commit();
           } else if (menuItem == R.id.nav_account){
               getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, accountFragment).commit();
           }
       });
       chipNavigationBar.setItemSelected(R.id.nav_home,true);
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}