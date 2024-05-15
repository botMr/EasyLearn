package com.example.easylearn.accountFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.easylearn.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingsAccountActivity extends AppCompatActivity {
    private Uri filePath;
    TextView username_settings;
    CircleImageView image_settings;
    EditText email_edit_settings,password_edit_settings,username_edit_settings;
    ImageView edit_new_email,edit_new_password,edit_new_username;
    FloatingActionButton save_new_data;
    ImageButton backAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings_account);

        image_settings = findViewById(R.id.image_settings);
        username_settings = findViewById(R.id.username_settings);
        email_edit_settings = findViewById(R.id.email_edit_settings);
        password_edit_settings = findViewById(R.id.password_edit_settings);
        username_edit_settings = findViewById(R.id.username_edit_settings);
        edit_new_email = findViewById(R.id.edit_new_email);
        edit_new_password = findViewById(R.id.edit_new_password);
        edit_new_username = findViewById(R.id.edit_new_username);
        save_new_data = findViewById(R.id.save_new_data);
        backAccount = findViewById(R.id.backAccount);

        loadUserName();
        updateDataAccount();
        image_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        backAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void updateDataAccount() {
        email_edit_settings.setEnabled(false);
        password_edit_settings.setEnabled(false);
        username_edit_settings.setEnabled(false);
        edit_new_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_edit_settings.setEnabled(true);
                email_edit_settings.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(email_edit_settings, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        edit_new_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password_edit_settings.setEnabled(true);
                password_edit_settings.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(password_edit_settings, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        edit_new_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_edit_settings.setEnabled(true);
                username_edit_settings.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(username_edit_settings, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        save_new_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Map<String, Object> update = new HashMap<>();
                                update.put("email",email_edit_settings.getText().toString());
                                update.put("password", password_edit_settings.getText().toString());
                                update.put("username", username_edit_settings.getText().toString());
                                FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .updateChildren(update);
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String currentEmail = user.getEmail();
                if (user != null){
                    if (!password_edit_settings.getText().toString().isEmpty()){
                        user.updatePassword(password_edit_settings.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplication(),"Пароль обновлен",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplication(),"Не удалось обновить пароль",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    if (!email_edit_settings.getText().toString().isEmpty() && !email_edit_settings.getText().toString().equals(currentEmail)){
                        user.verifyBeforeUpdateEmail(email_edit_settings.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplication(), "Отправлено письмо для подтверждения", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplication(), "Не удалось отправить письмо", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }

                }


                email_edit_settings.setEnabled(false);
                password_edit_settings.setEnabled(false);
                username_edit_settings.setEnabled(false);
            }
        });
    }

    ActivityResultLauncher<Intent> pikeImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()== Activity.RESULT_OK && result.getData()!=null && result.getData().getData()!=null){
                filePath = result.getData().getData();

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(),filePath);
                    image_settings.setImageBitmap(bitmap);
                }catch (IOException e){
                    e.printStackTrace();
                }
                uploadImage();
            }
        }
    });
    public void loadUserName(){
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String username = snapshot.child("username").getValue().toString();
                        String profileImage = snapshot.child("profileImage").getValue().toString();
                        String email = snapshot.child("email").getValue().toString();
                        String password = snapshot.child("password").getValue().toString();
                        String text = "Привет, " + username + "!";
                        username_settings.setText(text);
                        username_edit_settings.setText(username);
                        email_edit_settings.setText(email);
                        password_edit_settings.setText(password);


                        if (!profileImage.isEmpty()){
                            Glide.with(getApplication()).load(profileImage).into(image_settings);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
    public void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        pikeImage.launch(intent);
    }
    public void uploadImage(){
        if (filePath!=null){
            String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

            FirebaseStorage.getInstance().getReference().child("images/"+uid)
                    .putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(getApplication(), "Photo upload complete", Toast.LENGTH_SHORT).show();

                            FirebaseStorage.getInstance().getReference().child("images/"+uid).getDownloadUrl()
                                    .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .child("profileImage").setValue(uri.toString());
                                        }
                                    });
                        }
                    });
        }
    }
}