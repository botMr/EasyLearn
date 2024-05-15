package com.example.easylearn.learnFragment.Theory;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.R;
import com.example.easylearn.learnFragment.LearnMaterilsFragment;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TheoryActivity extends AppCompatActivity {
    ImageButton back;
    TextView theoryName;
    TextView theoryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theory);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        String theoryNumberText = getIntent().getStringExtra("theoryNumber");
        String theoryUrl = getIntent().getStringExtra("theoryUrl");

        theoryName = findViewById(R.id.theory_name);
        theoryName.setText(theoryNumberText);

        theoryText = findViewById(R.id.theoryText);
        ApiConnection.downloadFromUrl(theoryUrl,theoryText);

        back = findViewById(R.id.back_theoryCards);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_learn_materials");
                if (fragment != null && fragment instanceof LearnMaterilsFragment){
                    ((LearnMaterilsFragment) fragment).onBackPressed();
                } else {
                    TheoryActivity.super.onBackPressed();
                }
            }
        });

    }

    private class DownloadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                urlConnection.disconnect();
                return stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                theoryText.setText(result);
            } else {
                theoryText.setText("Error downloading document");
            }
        }
    }

}

