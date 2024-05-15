package com.example.easylearn.practiceFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class GptBottomSheet extends BottomSheetDialogFragment {
    Button send_request;
    TextView ai_response;
    ImageButton close_sheet_gpt,help_btn;
    Spinner spinnerTopic,spinnerExample;
    Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ai_alert, container, false);

        send_request = view.findViewById(R.id.send_request);
        close_sheet_gpt = view.findViewById(R.id.close_sheet_gpt);
        ai_response = view.findViewById(R.id.ai_response);
        spinnerTopic = view.findViewById(R.id.spinner_topic);
        spinnerExample = view.findViewById(R.id.spinner_example);
        help_btn = view.findViewById(R.id.help_btn);
        help_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpAlert();
                dialog.show();
            }
        });
        close_sheet_gpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        send_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = spinnerTopic.getSelectedItem().toString();
                String example = spinnerExample.getSelectedItem().toString();
                String question = ("Приведи пример " + example + " " + "для сочинения на тему " + topic).toString().trim();
                ApiConnection.callGpt(question,getContext(),ai_response);
            }
        });

        return view;
    }
    private void helpAlert(){
            dialog = new Dialog(getContext());
            dialog.setContentView(R.layout.help_ai);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.round_shape));
    }
}

