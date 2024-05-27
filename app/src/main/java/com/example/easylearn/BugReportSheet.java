package com.example.easylearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BugReportSheet extends BottomSheetDialogFragment {
    Button send_bug_report;
    EditText bug_text;
    Spinner bug_theme;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bug_report_alert, container, false);

        send_bug_report = view.findViewById(R.id.send_bug_report);
        bug_text = view.findViewById(R.id.bug_text);
        bug_theme = view.findViewById(R.id.bug_theme);

        send_bug_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = "vasap283@gmail.com".toString();
                String object = bug_theme.getSelectedItem().toString();
                String bug_message = bug_text.getText().toString();

                Intent mIntent = new Intent(Intent.ACTION_SEND);
                mIntent.setType("text/plain");
                mIntent.putExtra(Intent.EXTRA_EMAIL,new String[]{email});
                mIntent.putExtra(Intent.EXTRA_SUBJECT, object);
                mIntent.putExtra(Intent.EXTRA_TEXT, bug_message);

                try {
                    startActivity(Intent.createChooser(mIntent,"Send Email"));
                } catch (Exception e){
                    Toast.makeText(getContext(), e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }
}
