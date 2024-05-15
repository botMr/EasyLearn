package com.example.easylearn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.databinding.ActivityRegistrationBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registration extends AppCompatActivity {

    private ActivityRegistrationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HideSystemUI.hideSystemUI(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.usernameEdit.getText().toString().isEmpty() || binding.emailEdit.getText().toString().isEmpty() ||
                binding.passEdit.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEdit.getText().toString(), binding.passEdit.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        HashMap<String, String> userInfo = new HashMap<>();
                                        userInfo.put("email", binding.emailEdit.getText().toString());
                                        userInfo.put("password", binding.passEdit.getText().toString());
                                        userInfo.put("profileImage","");
                                        userInfo.put("username", binding.usernameEdit.getText().toString());
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .setValue(userInfo);
                                        startActivity(new Intent(Registration.this, HomePage.class));
                                    }
                                }
                            });

                }
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, Authorization.class));
            }
        });
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}