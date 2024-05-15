package com.example.easylearn.libraryFragment;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.example.easylearn.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SettingsDialogManager {
    private BottomSheetDialog bottomSheetDialog;
    private Context context;
    private TextView textBook;
    private View setBackgroundColor;

    public SettingsDialogManager(Context context, TextView textBook, View setBackgroundColor) {
        this.context = context;
        this.textBook = textBook;
        this.setBackgroundColor = setBackgroundColor;
    }

    public void showSettingsDialog() {
        if (bottomSheetDialog == null) {
            bottomSheetDialog = new BottomSheetDialog(context);
            bottomSheetDialog.setContentView(R.layout.text_settings);

            SeekBar seekBar_textSize = bottomSheetDialog.findViewById(R.id.seekBar_textSize);
            SwitchCompat switchMode = bottomSheetDialog.findViewById(R.id.switchMode);

            seekBar_textSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    float textSize = 15 + progress;
                    textBook.setTextSize(textSize);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

            switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        setBackgroundColor.setBackgroundColor(context.getResources().getColor(R.color.black));
                        textBook.setTextColor(context.getResources().getColor(R.color.white));
                    } else {
                        setBackgroundColor.setBackgroundColor(context.getResources().getColor(R.color.white));
                        textBook.setTextColor(context.getResources().getColor(R.color.black));
                    }
                }
            });
        }
        bottomSheetDialog.show();
    }
}
