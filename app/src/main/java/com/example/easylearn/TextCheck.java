package com.example.easylearn;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class TextCheck extends AppCompatActivity {

     RecyclerView recyclerView;
     MessageAdapter messageAdapter;
     List<Message> messages = new ArrayList<>();

    ArrayList<String> dataArray;

    ImageButton sendButton;
    ImageButton backButton;

    EditText messageEdit;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_check);

        recyclerView = findViewById(R.id.idChat);
        sendButton = findViewById(R.id.sendButton);
        messageEdit = findViewById(R.id.messageEdit);
        backButton = findViewById(R.id.back_Button);
        messageAdapter = new MessageAdapter(messages);
        recyclerView.setAdapter(messageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadArrayFromFile("data.txt");

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_gpt");
                if (fragment != null && fragment instanceof GptFragment){
                    ((GptFragment) fragment).onBackPressed();
                } else {
                    TextCheck.super.onBackPressed();
                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = messageEdit.getText().toString();
                Message message = new Message(userInput);
                messages.add(message);
                messageAdapter.notifyDataSetChanged();

                double maxSimilarity = 0;

                for (String data : dataArray) {
                    double similarity = calculateSimilarity(userInput, data);
                    if (similarity > maxSimilarity) {
                        maxSimilarity = similarity;
                    }
                }
                double precent = (1- maxSimilarity) * 100;
                String finalScore = String.format("%.2f", precent);
                Message botMessage = new Message("Уникальность текста пользователя: " + finalScore + "%");
                messages.add(botMessage);
                messageAdapter.notifyDataSetChanged();

            }
        });
    }

    private void loadArrayFromFile(String fileName) {
        dataArray = new ArrayList<>();
        try {
            InputStream is = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                for (String part : parts) {
                    dataArray.add(part);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double calculateSimilarity(String s1, String s2) {

        int shingleSize = 4;
        ArrayList<String> s1Shingles = getShingles(s1, shingleSize);
        ArrayList<String> s2Shingles = getShingles(s2, shingleSize);


        int intersection = 0;

        ArrayList<String> dictionary = loadDictionary();

        for (String shingle : s1Shingles) {
            if (!dictionary.contains(shingle) && s2Shingles.contains(shingle)) {
                intersection++;
            }
        }

        int union = s1Shingles.size() + s2Shingles.size() - intersection;

        return (double) intersection / union;
    }

    @NonNull
    private ArrayList<String> getShingles(String text, int shingleSize) {
        ArrayList<String> shingles = new ArrayList<>();
        for (int i = 0; i < text.length() - shingleSize + 1; i++) {
            shingles.add(text.substring(i, i + shingleSize));
        }
        return shingles;
    }

    @NonNull
    private ArrayList<String> loadDictionary() {
        ArrayList<String> dictionary = new ArrayList<>();
        try {
            InputStream is = getAssets().open("pronouns.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("@");
                for (String part : parts) {
                    dictionary.add(part);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dictionary;
    }
}

