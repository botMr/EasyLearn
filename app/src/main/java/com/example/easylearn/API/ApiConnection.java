package com.example.easylearn.API;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiConnection {

    public static void callGpt(String question, Context context, TextView ai_response) {

        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
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
        JSONObject userMessage = new JSONObject();
        JSONObject systemMessage = new JSONObject();
        try {
            systemMessage.put("role", "system");
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
            json.put("modelUri", ApiKeys.API_GPT_MODEL_URI_ANSWER);
            json.put("completionOptions", completionOptions);
            json.put("messages", messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://llm.api.cloud.yandex.net/foundationModels/v1/completion")
                .header("Authorization", ApiKeys.API_GPT_KEY)
                .header("x-folder-id", ApiKeys.API_GPT_FOLDER)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(context, "Не удалось загрузить ответ потому что" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(text);
                        JSONObject resultObject = jsonObject.getJSONObject("result");
                        JSONArray alternativesArray = resultObject.getJSONArray("alternatives");
                        JSONObject messageObject = alternativesArray.getJSONObject(0).getJSONObject("message");
                        String textValue = messageObject.getString("text");
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ai_response.setText(textValue);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Не удалось загрузить ответ потому что" + response.body().string(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public static void addTextCheck(final String text, final TextView check_response) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String responseJson = "";
                String result = null;
                OkHttpClient client = new OkHttpClient();
                MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
                String userKey = ApiKeys.API_TEXT_RU;
                String requestBody = "userkey=" + userKey + "&text=" + text;
                RequestBody body = RequestBody.create(mediaType, requestBody);
                Request request = new Request.Builder()
                        .url("https://api.text.ru/post")
                        .post(body)
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        responseJson = response.body().string();
                        JSONObject jsonObject = new JSONObject(responseJson);
                        String uid = jsonObject.getString("text_uid");
                        try {
                            Thread.sleep(360000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        result = ApiConnection.receiveText(uid);
                        return result;
                    } else {
                        return "Unsuccessful response: " + response.message();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                check_response.setText(result.toString());
            }
        }.execute();
    }
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .into(imageView);
    }
    public static String receiveText(String uid) {
        String responseJson = "";
        String result = null;
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String userKey = ApiKeys.API_TEXT_RU;
        String json = "detail_view";
        String requestBody = "userkey=" + userKey + "&uid=" + uid + "&jsonvisible=" + json;

        RequestBody body = RequestBody.create(mediaType, requestBody);
        Request request = new Request.Builder()
                .url("https://api.text.ru/post")
                .post(body)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                responseJson = response.body().string();
                JSONObject obj = new JSONObject(responseJson);
                double unique = obj.getDouble("unique");
                JSONObject seoCheck = new JSONObject(obj.getString("seo_check"));
                int waterPercent = seoCheck.getInt("water_percent");
                int countWords = seoCheck.getInt("count_words");
                JSONArray spellCheck = new JSONArray(obj.getString("spell_check"));
                int error = spellCheck.length();
                result = "Уникальность текста: " + unique + "\n\nПроцент воды в тексте: " + waterPercent + "\n\nКоличество слов в тексте: " + countWords + "\n\nКоличество ошибок в тексте: " + error;
                return result;
            } else {
                return "Unsuccessful response: " + response.message();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    public static void downloadFromUrl(String url, TextView setText) {
        new AsyncTask<String, Void, String>() {
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
                    setText.setText(result);
                } else {
                    setText.setText("Error downloading document");
                }
            }
        }.execute(url);
    }
    public static void callGptTextCheck(String text_check, Context context, TextView ai_response) {
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        //okhttp
        JSONObject completionOptions = new JSONObject();
        try {
            completionOptions.put("stream", false);
            completionOptions.put("temperature", 0.2);
            completionOptions.put("maxTokens", "1200");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray messages = new JSONArray();
        JSONObject userMessage = new JSONObject();
        JSONObject systemMessage = new JSONObject();
        try {
            systemMessage.put("role", "system");
            systemMessage.put("text", "Ты проверяющий сочинения учеников писавших ЕГЭ по русскому языку. Проверь сочинение . В качестве ответа напиши примерный балл сочинения используя критерии из ФИПИ по ЕГЭ 2024 года. Также укажи примерный бал по каждому из критериев.");
            userMessage.put("role", "user");
            userMessage.put("text", text_check);
            messages.put(systemMessage);
            messages.put(userMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject json = new JSONObject();
        try {
            json.put("modelUri", ApiKeys.API_GPT_MODEL_URI_CHECK);
            json.put("completionOptions", completionOptions);
            json.put("messages", messages);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url("https://llm.api.cloud.yandex.net/foundationModels/v1/completion")
                .header("Authorization", ApiKeys.API_GPT_KEY)
                .header("x-folder-id", ApiKeys.API_GPT_FOLDER)
                .post(body)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(context, "Не удалось загрузить ответ потому что" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String text = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(text);
                        JSONObject resultObject = jsonObject.getJSONObject("result");
                        JSONArray alternativesArray = resultObject.getJSONArray("alternatives");
                        JSONObject messageObject = alternativesArray.getJSONObject(0).getJSONObject("message");
                        String textValue = messageObject.getString("text");
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ai_response.setText(textValue);
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(context, "Не удалось загрузить ответ потому что" + response.body().string(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
