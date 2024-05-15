package com.example.easylearn.learnFragment.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;

import java.util.List;

public class DictionaryPhraselAdapter extends RecyclerView.Adapter<DictionaryPhraselAdapter.ViewHolder> {
    private List<Phrase> phraseList;
    private Context context;

    public DictionaryPhraselAdapter(List<Phrase> phraseList, Context context){
        this.phraseList = phraseList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Phrase phrase = phraseList.get(position);
        holder.wordText.setText(phrase.getPhrase());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String explanation = context.getString(context.getResources()
                        .getIdentifier("explanation" + (holder.getAdapterPosition()+1),"string", context.getPackageName()));
                Intent intent = new Intent(context, ExplainActivity.class);
                intent.putExtra("explanation",explanation);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return phraseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView wordText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wordText = itemView.findViewById(R.id.textView_word);
        }
    }
}
