package com.example.easylearn;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TextMistakesChecker extends AppCompatActivity {
    EditText textToCheck;
    ImageButton sendButton;
    ImageButton backButton;
    RecyclerView recyclerView;
    List<ChatMessages> chatMessagesList;
    ChatMessageAdapter chatMessageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_text_mistakes_checker);
        chatMessagesList = new ArrayList<>();

       textToCheck = findViewById(R.id.mistakesEdit);
       sendButton = findViewById(R.id.sendMistakesCheck);
       backButton = findViewById(R.id.back_mistakes_button);
       recyclerView = findViewById(R.id.idMistakes);

       chatMessageAdapter = new ChatMessageAdapter(chatMessagesList);
       recyclerView.setAdapter(chatMessageAdapter);
       LinearLayoutManager llm = new LinearLayoutManager(this);
       llm.setStackFromEnd(true);
       recyclerView.setLayoutManager(llm);

       backButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_gpt");
               if (fragment != null && fragment instanceof GptFragment){
                   ((GptFragment) fragment).onBackPressed();
               } else {
                   TextMistakesChecker.super.onBackPressed();
               }
           }
       });

       sendButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String textCheck = textToCheck.getText().toString().trim();
               addToChat(textCheck,ChatMessages.SENT_BY_ME);
               textToCheck.setText("");
               checkSpelling(textCheck);
           }
       });
    }
    void addToChat(String messages, String sentBy) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatMessagesList.add(new ChatMessages(messages,sentBy));
                chatMessageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(chatMessageAdapter.getItemCount());
            }
        });
    }


    private void checkSpelling(String text) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://speller.yandex.net/services/spellservice.json/checkText?text=" + text)
                .get()
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseString = response.body().string();
                    String decode = unicodeToNative(responseString);
                    int errorsCount = mistakeCounter(decode);
                    String answer = "Количество ошибок" + " - " + errorsCount;
                    addToChat(answer,ChatMessages.SENT_BY_BOT);
                }
            }
        });
    }

    public static String unicodeToNative(String input) {
        StringBuilder sb = new StringBuilder();
        int position = 0;
        while (position < input.length()) {
            int index = input.indexOf("\\u", position);
            if (index == -1) {
                sb.append(input.substring(position));
                break;
            }
            sb.append(input, position, index);
            position = index + 6;
            String unicode = input.substring(index + 2, index + 6);
            char letter = (char) Integer.parseInt(unicode, 16);
            sb.append(letter);
        }
        return sb.toString();
    }

    public static int mistakeCounter(String output) {
        int count = 0;
        int index = 0;

        while (index != -1) {
            index = output.indexOf("\"code\"", index);
            if (index != -1){
                count++;
                index += "\"code\"".length();
            }
        }
        return count;
    }

}