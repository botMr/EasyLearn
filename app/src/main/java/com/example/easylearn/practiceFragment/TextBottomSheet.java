package com.example.easylearn.practiceFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;

public class TextBottomSheet extends BottomSheetDialogFragment {
    private String textFromNoteActivity;
    ImageButton help_btn_text,close_sheet_text;
    Dialog dialog;
    Button send_text;
    TextView check_response;
    private static final int MAX_REQUESTS_PER_DAY = 2;
    private SharedPreferences sharedPreferences;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_check_alert, container, false);

        help_btn_text = view.findViewById(R.id.help_btn_text);
        close_sheet_text = view.findViewById(R.id.close_sheet_text);
        send_text = view.findViewById(R.id.send_text);
        check_response = view.findViewById(R.id.check_response);
        sharedPreferences = getActivity().getSharedPreferences("TextCheclPrefs", Context.MODE_PRIVATE);
        help_btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpAlert();
                dialog.show();
            }
        });
        close_sheet_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        send_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (canSendRequest()){
                    ApiConnection.addTextCheck(textFromNoteActivity,check_response);
                    updateRequestCount();
                } else {
                    String nextAvailableTime = getNextAvailableTime();
                    check_response.setText("Вы сможете отправить запрос снова " + nextAvailableTime);
                }

            }
        });

        return view;
    }
    private void helpAlert(){
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.text_help);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.round_shape));
    }

    private boolean canSendRequest() {
        long lastRequestTime = sharedPreferences.getLong("lastRequestTime", 0);
        int requestsToday = sharedPreferences.getInt("requestsToday", 0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(lastRequestTime);

        Calendar now = Calendar.getInstance();

        if (calendar.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) &&
                calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            return requestsToday < MAX_REQUESTS_PER_DAY;
        } else {
            sharedPreferences.edit().putInt("requestsToday", 0).apply();
            return true;
        }
    }

    private void updateRequestCount() {
        int requestsToday = sharedPreferences.getInt("requestsToday", 0);
        sharedPreferences.edit()
                .putInt("requestsToday", requestsToday + 1)
                .putLong("lastRequestTime", System.currentTimeMillis())
                .apply();
    }

    private String getNextAvailableTime() {
        long lastRequestTime = sharedPreferences.getLong("lastRequestTime", 0);
        Calendar nextAvailable = Calendar.getInstance();
        nextAvailable.setTimeInMillis(lastRequestTime);
        nextAvailable.add(Calendar.DAY_OF_YEAR, 1);

        return android.text.format.DateFormat.format("dd.MM.yyyy HH:mm:ss", nextAvailable).toString();
    }

    public void setTextFromNoteActivity(String text) {
        textFromNoteActivity = text;
    }
}