package com.example.easylearn.accountFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easylearn.R;

import java.util.ArrayList;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder>{
    Context context;
    ArrayList<Achievement> achievementList;

    public AchievementAdapter(Context context, ArrayList<Achievement> achievement) {
        this.context = context;
        this.achievementList = achievement;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievement_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Achievement achievement = achievementList.get(position);
        holder.achievement_text.setText(achievement.getText());
        if (achievement.isCompleted()) {
            holder.achievement_icon.setImageResource(achievement.getImageResource());
        } else {
            holder.achievement_icon.setImageResource(R.drawable.achivemetn_uncolor);
        }
    }

    public void checkAndUpdateAchievement(String achievementId) {
        for (int i = 0; i < achievementList.size(); i++) {
            Achievement achievement = achievementList.get(i);
            if (achievement.getId().equals(achievementId)) {
                achievement.setCompleted(true);
                achievement.setImageResource(R.drawable.achievement_color); // Замените на ваше изображение для выполненного достижения
                notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return achievementList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView achievement_text;
        ImageView achievement_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            achievement_text = itemView.findViewById(R.id.achievement_text);
            achievement_icon = itemView.findViewById(R.id.achievement_icon);
        }
    }
}
