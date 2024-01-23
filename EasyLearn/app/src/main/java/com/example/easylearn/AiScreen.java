package com.example.easylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AiScreen extends AppCompatActivity {
    TextView textView;
    ImageView imageView;

    RecyclerView recyclerView;
    EditText inputGPT;
    ImageButton sendBtn;
    List<Message> messageList;
    MessageAdapter messageAdapter;

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_screen);
        messageList = new ArrayList<>();

        textView = findViewById(R.id.textViewMark);
        imageView = findViewById(R.id.imageViewProfile);
        recyclerView = findViewById(R.id.recyclerView);
        inputGPT = findViewById(R.id.inputGPT);
        sendBtn = findViewById(R.id.sendBtn);

        //setup recycler view
        messageAdapter = new MessageAdapter(messageList);
        recyclerView.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recyclerView.setLayoutManager(llm);

        sendBtn.setOnClickListener((v)->{
            String question = inputGPT.getText().toString().trim();
            addToChat(question,Message.SENT_BY_ME);
            inputGPT.setText("");
            callAPI(question);
        });
    }

    void addToChat(String message,String sendBy){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                messageList.add(new Message(message,sendBy));
                messageAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
            }
        });
    }

    void addResponse(String response){
        addToChat(response,Message.SENT_BY_GPT);
    }

    void callAPI(String question){
        //okhttp
        JSONObject completionOptions = new JSONObject();
        try {
            completionOptions.put("stream", false);
            completionOptions.put("temperature", 0.6);
            completionOptions.put("maxTokens", "200");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray messages = new JSONArray();
        JSONObject systemMessage = new JSONObject();
        JSONObject userMessage = new JSONObject();
        try {
            systemMessage.put("role", "system");
            systemMessage.put("text", "Ты - репетитор по русскому языку. Ты помогаешь ученикам в написании сочинений, проводишь примеры введения, заключения и аргументов для текста. Так же ты отвечаешь на вопросы по русскому языку");

            userMessage.put("role", "user");
            userMessage.put("text", question);

            messages.put(systemMessage);
            messages.put(userMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        try {
            json.put("modelUri", "ds://bt1ccelku7mkeq2n8339");
            json.put("completionOptions", completionOptions);
            json.put("messages", messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://llm.api.cloud.yandex.net/foundationModels/v1/completion")
                .header("Authorization","Api-Key AQVNwJ5SIzzFeuKzE8KEwWkefRqkChxmicd6iG-r")
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
                    addResponse("Не удалось загрузить ответ потому что"+response.body().toString());
                }
            }
        });
        }

    public void ai_assistant_bar(View view) {
        startActivity(new Intent(AiScreen.this,MainActivity.class));
    }
}