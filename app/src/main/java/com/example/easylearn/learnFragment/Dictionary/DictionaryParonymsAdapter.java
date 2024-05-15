package com.example.easylearn.learnFragment.Dictionary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;

import java.util.ArrayList;
import java.util.List;

public class DictionaryParonymsAdapter extends RecyclerView.Adapter<DictionaryParonymsAdapter.ViewHolder> {
    private List<String> dataList;
    private Context context;

    public DictionaryParonymsAdapter(List<String> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String word = dataList.get(position);
        holder.wordText.setText(word);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView wordText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.textView_word);
        }
    }
}
