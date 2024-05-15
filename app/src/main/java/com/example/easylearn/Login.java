package com.example.easylearn;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.easylearn.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        HideSystemUI.hideSystemUI(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEdit.getText().toString().isEmpty() || binding.passwordEdit.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show();
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.emailEdit.getText().toString(), binding.passwordEdit.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        startActivity(new Intent(Login.this, HomePage.class));
                                    }
                                }
                            });
                }
            }
        });

        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Authorization.class));
            }
        });

        binding.forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_forgot_password, null);
                EditText emailBox = dialogView.findViewById(R.id.emailBox);

                builder.setView(dialogView);
                AlertDialog dialog = builder.create();

                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String userEmail = emailBox.getText().toString();

                        if (TextUtils.isEmpty(userEmail) || !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(Login.this,"Введите свою почту от аккаунта",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        FirebaseAuth.getInstance().sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Login.this,"Проверьте свою почту",Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Login.this,"Не забудьте обновить пароль в своем профиле",Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(Login.this,"Не удаось отправить письмо",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                if (dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }

                dialog.show();
            }
        });
    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}