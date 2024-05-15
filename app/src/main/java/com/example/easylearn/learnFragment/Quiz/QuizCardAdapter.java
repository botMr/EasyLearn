package com.example.easylearn.learnFragment.Quiz;


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

public class QuizCardAdapter extends RecyclerView.Adapter<QuizCardAdapter.ViewHolder> {

    ArrayList<QuizModel> list;
    Context context;

    public QuizCardAdapter(ArrayList<QuizModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final QuizModel model = list.get(position);
        holder.quizTop.setCardBackgroundColor(model.getColor());
        holder.quizNumber.setText(model.getQuizNumber());

        holder.quizCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, QuizActivity.class);
                intent.putExtra("quizNumber",model.getQuizNumber());
                intent.putExtra("int",model.getQuestionNumber());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView quizCard;
        CardView quizTop;
        TextView quizNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            quizCard = itemView.findViewById(R.id.quizCard);
            quizTop = itemView.findViewById(R.id.quizTop);
            quizNumber = itemView.findViewById(R.id.quizNumber);
        }
    }
}
