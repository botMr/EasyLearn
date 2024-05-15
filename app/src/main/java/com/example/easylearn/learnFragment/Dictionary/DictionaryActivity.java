package com.example.easylearn.learnFragment.Dictionary;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;
import com.example.easylearn.learnFragment.LearnMaterilsFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DictionaryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    TextView dictionaryName;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dictionary);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        String dictionaryText = getIntent().getStringExtra("dictionaryName");
        String adapterName = getIntent().getStringExtra("adapterName");

        dictionaryName = findViewById(R.id.dictionary_name);
        dictionaryName.setText(dictionaryText);

        backButton = findViewById(R.id.back_learnCards);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_learn_materials");
                if (fragment != null && fragment instanceof LearnMaterilsFragment){
                    ((LearnMaterilsFragment) fragment).onBackPressed();
                } else {
                    DictionaryActivity.super.onBackPressed();
                }
            }
        });


        recyclerView = findViewById(R.id.dictionary_cards);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setItemPrefetchEnabled(false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = getAdapterByName(adapterName);
        recyclerView.setAdapter(adapter);

    }
    private ArrayList<String> loadDictionaryAccents() {
        ArrayList<String> words = new ArrayList<>();
        try {
            InputStream is = getAssets().open("dictionaryAccents.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                for (String part : parts) {
                    words.add(part);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
    private List<Phrase> loadDictionaryPhraselogical() {
        List<Phrase> phraseWords = new ArrayList<>();
        try {
            InputStream is = getAssets().open("dictionaryPhraseologicalUnits.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                for (String part : parts) {
                    phraseWords.add(new Phrase(part));
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phraseWords;
    }
    private List<String> loadDictionaryParonyms() {
        List<String> dataList = new ArrayList<>();
      try {
          InputStream is = getAssets().open("dcitionaryParonyms.txt");
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
          String line;
          while ((line = bufferedReader.readLine())!= null){
              String[] items = line.split("@");
              for (String item : items){
                  dataList.add(item);
              }
          }
          bufferedReader.close();
      } catch (IOException e){
          e.printStackTrace();
      }
      return dataList;
    }
    private RecyclerView.Adapter getAdapterByName(String name) {
        switch (name) {
            case "DictionaryAdapter":
                ArrayList<String> words = loadDictionaryAccents();
                return new DictionaryAccentsAdapter(words);
            case "DictionaryPhraselogicalAdapter":
                List<Phrase> phraseWords = loadDictionaryPhraselogical();
                return  new DictionaryPhraselAdapter(phraseWords,this);
            case "DictionaryParonymsAdapter":
                List<String> paronyms = loadDictionaryParonyms();
                return new DictionaryParonymsAdapter(paronyms,this);
            default:
                return null;
        }
    }

}