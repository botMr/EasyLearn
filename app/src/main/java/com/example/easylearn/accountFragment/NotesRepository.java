package com.example.easylearn.accountFragment;

import android.content.Context;
import android.content.SharedPreferences;

public class NotesRepository {

    private SharedPreferences prefs;
    public NotesRepository(Context context) {
        prefs = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    public void saveNotesCount(int count) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("notesCount", count);
        editor.apply();
    }

    public int getNotesCount() {
        return prefs.getInt("notesCount", 0);
    }
}
