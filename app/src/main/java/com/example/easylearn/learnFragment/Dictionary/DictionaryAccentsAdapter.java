package com.example.easylearn.learnFragment.Dictionary;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;

import java.util.ArrayList;

public class DictionaryAccentsAdapter extends RecyclerView.Adapter<DictionaryAccentsAdapter.viewHolder>{
    ArrayList<String> words;

    public DictionaryAccentsAdapter(ArrayList<String> words) {
        this.words = words;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        String word = words.get(position);
        holder.wordText.setText(word);

        SpannableStringBuilder spannable = new SpannableStringBuilder(word);
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                spannable.setSpan(new ForegroundColorSpan(Color.parseColor("#BB1E1E")), i, i+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        holder.wordText.setText(spannable);
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView wordText;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.textView_word);
        }
    }
}
