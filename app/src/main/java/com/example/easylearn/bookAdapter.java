package com.example.easylearn;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.units.qual.C;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class bookAdapter extends RecyclerView.Adapter<bookAdapter.viewHolder> {

    ArrayList<SetsModel> list;
    Context context;

    public bookAdapter(ArrayList<SetsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final  SetsModel model = list.get(position);
        holder.setBookName.setText(model.getSetBookName());
        holder.setAuthorName.setText(model.getSetAuthorName());
        holder.setBookInfo.setText(model.getSetBookInfo());
        holder.cardView.setCardBackgroundColor(model.getColor());

        holder.expandInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.expandLayout.getVisibility() == View.GONE){
                    holder.expandLayout.setVisibility(View.VISIBLE);
                    holder.expandInfo.setImageResource(R.drawable.arrow_up);
                } else {
                    holder.expandLayout.setVisibility(View.GONE);
                    holder.expandInfo.setImageResource(R.drawable.arrow_down);
                }
            }
        });

        holder.audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AudioActivity.class);
                intent.putExtra("audioURL", model.getAudioURL());
                intent.putExtra("bookName", model.getAudioBookName());
                context.startActivity(intent);
            }
        });

        holder.textBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("bookTextURL", model.getTextBookURL());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class viewHolder extends RecyclerView.ViewHolder {

        TextView setBookName;
        TextView setAuthorName;
        TextView setBookInfo;
        ImageView expandInfo;
        ImageView audioBtn;
        ImageView textBtn;
        CardView cardView;

        LinearLayout expandLayout;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            setBookName = itemView.findViewById(R.id.setBookName);
            setAuthorName = itemView.findViewById(R.id.setAuthorName);
            setBookInfo = itemView.findViewById(R.id.setBookInfo);
            expandInfo = itemView.findViewById(R.id.expandInfo);
            audioBtn = itemView.findViewById(R.id.audioBtn);
            textBtn = itemView.findViewById(R.id.textBtn);
            cardView = itemView.findViewById(R.id.bookCard);
            expandLayout = itemView.findViewById(R.id.expandLayout);
        }
    }


}
