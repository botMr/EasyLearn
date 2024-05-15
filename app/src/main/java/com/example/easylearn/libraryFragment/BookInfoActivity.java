package com.example.easylearn.libraryFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.easylearn.API.ApiConnection;
import com.example.easylearn.HideSystemUI;
import com.example.easylearn.R;

public class BookInfoActivity extends AppCompatActivity {
    ImageButton backLibrary,listen_book;
    ImageView bookInfoCover;
    TextView bookInfo_name,bookInfo_author,bookInfo_text;
    Button read_book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_info);
        HideSystemUI.hideSystemUI(this);

        String bookCover = getIntent().getStringExtra("bookCover");
        String bookName = getIntent().getStringExtra("bookName");
        String bookAuthor = getIntent().getStringExtra("bookAuthor");
        String bookInfo = getIntent().getStringExtra("bookInfo");
        String bookTextUrl = getIntent().getStringExtra("bookTextUrl");
        String audioBookUrl = getIntent().getStringExtra("audioBookUrl");

        backLibrary = findViewById(R.id.backLibrary);
        bookInfoCover = findViewById(R.id.bookInfoCover);
        bookInfo_name = findViewById(R.id.bookInfo_name);
        bookInfo_author = findViewById(R.id.bookInfo_author);
        bookInfo_text = findViewById(R.id.bookInfo_text);
        read_book = findViewById(R.id.read_book);
        listen_book = findViewById(R.id.listen_book);

        ApiConnection.loadImage(this,bookCover,bookInfoCover);
        bookInfo_name.setText(bookName);
        bookInfo_author.setText(bookAuthor);
        bookInfo_text.setText(bookInfo);
        backLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentByTag("fragment_library");
                if (fragment != null && fragment instanceof LibraryFragment){
                    ((LibraryFragment) fragment).onBackPressed();
                } else {
                    BookInfoActivity.super.onBackPressed();
                }
            }
        });
        read_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookInfoActivity.this, BookActivity.class);
                intent.putExtra("bookText",bookTextUrl);
                startActivity(intent);
            }
        });
        listen_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookInfoActivity.this, AudioActivity.class);
                intent.putExtra("bookCover",bookCover);
                intent.putExtra("audioBookUrl",audioBookUrl);
                startActivity(intent);
            }
        });

    }
    public void onResume(){
        super.onResume();
        HideSystemUI.hideSystemUI(this);
    }
}