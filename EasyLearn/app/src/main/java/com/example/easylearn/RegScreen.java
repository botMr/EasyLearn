package com.example.easylearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.easylearn.databinding.ActivityRegScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class RegScreen extends AppCompatActivity {

    private ActivityRegScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.editTextEmail.getText().toString().isEmpty() || binding.editTextPassword.getText().toString().isEmpty() || binding.edittextName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.editTextEmail.getText().toString(),binding.editTextPassword.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.edittextName.getText().toString());
                                        userInfo.put("username",binding.edittextName.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid()).setValue(userInfo);

                                        startActivity(new Intent(RegScreen.this, MainActivity.class));
                                    }
                                }
                            });
                }
            }
        });
    }

    public void back(View view) {
        Intent intent = new Intent(this, LogRegScreen.class);
        startActivity(intent);
    }
}