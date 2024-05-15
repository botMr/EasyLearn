package com.example.easylearn.learnFragment.Theory;

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

import java.util.ArrayList;

public class TheoryCardAdapter extends RecyclerView.Adapter<TheoryCardAdapter.ViewHolder> {

    ArrayList<TheoryCardModel> list;
    Context context;

    public TheoryCardAdapter(ArrayList<TheoryCardModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.theory_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final TheoryCardModel model = list.get(position);
        holder.theoryTop.setCardBackgroundColor(model.getColor());
        holder.theoryNumber.setText(model.getTheoryNumber());

        holder.theoryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TheoryActivity.class);
                intent.putExtra("theoryNumber",model.getTheoryNumber());
                intent.putExtra("theoryUrl",model.getTheoryUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView theoryCard;
        CardView theoryTop;
        TextView theoryNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theoryCard = itemView.findViewById(R.id.theoryCard);
            theoryTop = itemView.findViewById(R.id.theoryTop);
            theoryNumber = itemView.findViewById(R.id.theoryNumber);
        }
    }
}
