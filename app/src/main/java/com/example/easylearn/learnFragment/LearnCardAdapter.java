package com.example.easylearn.learnFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;
import com.example.easylearn.learnFragment.Dictionary.DictionaryActivity;

import java.util.ArrayList;

public class LearnCardAdapter extends RecyclerView.Adapter<LearnCardAdapter.viewHolder>{
    ArrayList<LearnCardModel> list;
    Context context;

    public LearnCardAdapter(ArrayList<LearnCardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.learn_card,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        final LearnCardModel model = list.get(position);
        holder.cardTop.setCardBackgroundColor(model.getColor());
        holder.learnCardText.setText(model.getLearnCardText());
        holder.cardSubject.setText(model.getCardSubject());
        holder.itemCount.setText(model.getItemCount());

        holder.learnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DictionaryActivity.class);
                intent.putExtra("dictionaryName",model.getCardSubject());
                intent.putExtra("adapterName",model.getAdapterName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        CardView learnCard;
        CardView cardTop;
        TextView learnCardText;
        TextView cardSubject;
        TextView itemCount;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            learnCard = itemView.findViewById(R.id.learnCard);
            cardTop = itemView.findViewById(R.id.cardTop);
            learnCardText = itemView.findViewById(R.id.learnCard_text);
            cardSubject = itemView.findViewById(R.id.cardSubject);
            itemCount = itemView.findViewById(R.id.item_count);
        }
    }
}
