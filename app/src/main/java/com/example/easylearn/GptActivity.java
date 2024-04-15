package com.example.easylearn;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GptActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ChatMessages> chatMessagesList;
    ChatMessageAdapter chatMessageAdapter;

    Spinner spinnerTheme, spinnerSubTheme;
    ImageButton sendGPT;
    ImageButton backButton;
    public  static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .build();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gpt);
        chatMessagesList = new ArrayList<>();

        sendGPT = findViewById(R.id.send_Gpt);
        recyclerView = findViewById(R.id.id_gpt_view);
        backButton = findViewById(R.id.back_button);

        chatMessageAdapter = new ChatMessageAdapter(chatMessagesList);
        recyclerView.setAdapter(chatMessageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        spinnerTheme = findViewById(R.id.spinnerTheme);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.theme_array, R.layout.selected_item);
        adapter1.setDropDownViewResource(R.layout.dropdown_item);
        spinnerTheme.setAdapter(adapter1);

        spinnerSubTheme = findViewById(R.id.spinnerSubTheme);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.sub_theme_array, R.layout.selected_item);
        adapter2.setDropDownViewResource(R.layout.dropdown_item);
        spinnerSubTheme.setAdapter(adapter2);

        sendGPT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedTheme1 = spinnerTheme.getSelectedItem().toString();
                String selectedTheme2 = spinnerSubTheme.getSelectedItem().toString();

                String question = ("Приведи пример " + selectedTheme2 + " " + "для сочинения на тему " + selectedTheme1).toString().trim();
                addToChat(question,ChatMessages.SENT_BY_ME);
                callGpt(question);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_gpt");
                if (fragment != null && fragment instanceof GptFragment){
                    ((GptFragment) fragment).onBackPressed();
                } else {
                    GptActivity.super.onBackPressed();
                }
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

    void addResponse(String response){
        addToChat(response,ChatMessages.SENT_BY_BOT);
    }

    void callGpt(String question){
        //okhttp
        JSONObject completionOptions = new JSONObject();
        try {
            completionOptions.put("stream", false);
            completionOptions.put("temperature", 0.6);
            completionOptions.put("maxTokens", "200");
        }catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        JSONObject systemMessage = new JSONObject();
        try {
            systemMessage.put("role","system");
            systemMessage.put("text", "Твое имя Денис. \\n Ты отвечаешь от лица мужского рода \\nТвое предназначение – отвечать на вопросы, помогать ученикам.\\nТы эксперт в сфере написания сочинений.");
            userMessage.put("role", "user");
            userMessage.put("text", question);
            messages.put(systemMessage);
            messages.put(userMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        try {
            json.put("modelUri", "ds://bt1scborbuqq9c0v7fa0");
            json.put("completionOptions", completionOptions);
            json.put("messages", messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://llm.api.cloud.yandex.net/foundationModels/v1/completion")
                .header("Authorization","Api-Key AQVN0lXfYltafutHUvYzUAX_GHs-U3u2znXIoQ_F")
                .header("x-folder-id", "b1g1u2pbs0tgd2e5sgqd")
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Не удалось загрузить ответ потому что"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String text = response.body().string();
                    try{
                        JSONObject jsonObject = new JSONObject(text);
                        JSONObject resultObject = jsonObject.getJSONObject("result");
                        JSONArray alternativesArray = resultObject.getJSONArray("alternatives");
                        JSONObject messageObject = alternativesArray.getJSONObject(0).getJSONObject("message");
                        String textValue = messageObject.getString("text");
                        addResponse(textValue.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    addResponse("Не удалось загрузить ответ потому что"+response.body().string());
                }
            }
        });

    }

}
