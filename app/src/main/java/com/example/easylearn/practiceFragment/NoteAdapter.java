package com.example.easylearn.practiceFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;
import com.example.easylearn.accountFragment.SharedViewModelNoteCount;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder>{
    Context context;
    RealmResults<Note> noteList;

    private SharedViewModelNoteCount sharedViewModel;

    public NoteAdapter(Context context, RealmResults<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.textView_tittle.setText(note.getTitle());
        holder.textView_notes.setText(note.getDescription());
        String formattedTime = DateFormat.getDateTimeInstance().format(note.createdTime);
        holder.textView_dat.setText(formattedTime);

        holder.itemView.setOnLongClickListener(v -> {
            PopupMenu menu = new PopupMenu(context, v);
            menu.getMenu().add("Удалить");
            menu.setOnMenuItemClickListener(item -> {
                if ("Удалить".equals(item.getTitle())) {
                    deleteNote(note, position);
                }
                return true;
            });
            menu.show();
            return false;
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NoteActivity.class);
                intent.putExtra("notePosition", position);
                context.startActivity(intent);
            }
        });
    }

    private void deleteNote(Note note, int position) {
        Realm realm = Realm.getDefaultInstance();
        // Получаем первичный ключ заметки, который нужен для поиска в фоновом потоке
        final long noteId = note.getCreatedTime();
        realm.executeTransactionAsync(r -> {
            // Поиск и удаление заметки в фоновом потоке
            Note noteToDelete = r.where(Note.class).equalTo("createdTime", noteId).findFirst();
            if (noteToDelete != null) {
                noteToDelete.deleteFromRealm();
            }
        }, () -> {
            // Этот код выполнится в главном потоке после успешного удаления
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
            Toast.makeText(context, "Заметка удалена", Toast.LENGTH_SHORT).show();
        }, error -> {
            // Обработка ошибок, можете добавить здесь свой код для логирования или отображения ошибки
            error.printStackTrace();
        });
    }
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder{
        TextView textView_tittle, textView_notes, textView_dat;
        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_tittle = itemView.findViewById(R.id.textView_tittle);
            textView_notes = itemView.findViewById(R.id.textView_notes);
            textView_dat = itemView.findViewById(R.id.textView_dat);
        }
    }
}
