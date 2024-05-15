package com.example.easylearn;

import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoadData {

    private CircleImageView circleImageView;
    private TextView userNameView;

    public LoadData loadImage(CircleImageView circleImageView, TextView userNameView,String text) {
        // Получение ссылки на базу данных Firebase
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        // Загрузка username и photoUrl
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("username").getValue().toString();
                String profileImage = dataSnapshot.child("profileImage").getValue().toString();
                String finalText = text + username;
                userNameView.setText(finalText);

                // Установка имени пользователя и изображения в CircleImageView с помощью Glide
                if (profileImage != null) {
                    Glide.with(circleImageView.getContext())
                            .load(profileImage)
                            .into(circleImageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Обработка ошибок, например, вывод в лог
                Log.w("FirebaseImageLoader", "loadImage:onCancelled", databaseError.toException());
            }
        });
        return null;
    }
}
