package com.example.easylearn.practiceFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.HideSystemUI;
import com.example.easylearn.R;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class NoteActivity extends AppCompatActivity {
    EditText editText_title, editText_notes;
    ImageView imageView_save, imageView_ai, imageView_check;
    Realm realm;
    Note notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_note);
        HideSystemUI.hideSystemUI(this);


        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);
        imageView_ai = findViewById(R.id.imageView_ai);
        imageView_check = findViewById(R.id.imageView_check);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(config);

        int notePosition = getIntent().getIntExtra("notePosition", -1);
        if (notePosition != -1) {
            // Загружаем заметку из базы данных по позиции
            RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).findAll();
            Note note = notesList.get(notePosition);
            if (note != null) {
                editText_title.setText(note.getTitle());
                editText_notes.setText(note.getDescription());
                // Сохраняем ссылку на заметку для последующего обновления
                notes = note;
            }
        }

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String description = editText_notes.getText().toString();

                realm.beginTransaction();
                if (notes == null) {
                    // Создаем новую заметку, если не была передана существующая
                    notes = realm.createObject(Note.class);
                    Toast.makeText(getApplicationContext(),"Cочинение создано",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Сочинение обновлено",Toast.LENGTH_SHORT).show();
                }
                // Обновляем поля заметки
                notes.setTitle(title);
                notes.setDescription(description);
                notes.setCreatedTime(System.currentTimeMillis());
                realm.commitTransaction();
                finish();
            }
        });

        imageView_ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textCheck = editText_notes.getText().toString();
                GptBottomSheet gptBottomSheet = new GptBottomSheet();
                gptBottomSheet.setTextFromNoteActivity(textCheck);
                gptBottomSheet.show(getSupportFragmentManager(),"gptSheet");
            }
        });

        imageView_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textCheck = editText_notes.getText().toString();
                TextBottomSheet textBottomSheet = new TextBottomSheet();
                textBottomSheet.setTextFromNoteActivity(textCheck);
                textBottomSheet.show(getSupportFragmentManager(),"textSheet");
            }
        });

    }
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}