package com.example.easylearn.libraryFragment;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class BookModel {
    private String cardBookName;
    private String cardBookAuthor;
    private String audioBookUrl;
    private String textBookURL;
    private String bookCover;
    private String bookInfo;

    public BookModel(String cardBookName, String cardBookAuthor, String audioBookUrl, String textBookURL, String bookCover, String bookInfo) {
        this.cardBookName = cardBookName;
        this.cardBookAuthor = cardBookAuthor;
        this.audioBookUrl = audioBookUrl;
        this.textBookURL = textBookURL;
        this.bookCover = bookCover;
        this.bookInfo = bookInfo;
    }

    public String getCardBookName() {
        return cardBookName;
    }

    public void setCardBookName(String cardBookName) {
        this.cardBookName = cardBookName;
    }

    public String getCardBookAuthor() {
        return cardBookAuthor;
    }

    public void setCardBookAuthor(String cardBookAuthor) {
        this.cardBookAuthor = cardBookAuthor;
    }

    public String getAudioBookUrl() {
        return audioBookUrl;
    }

    public void setAudioBookUrl(String audioBookUrl) {
        this.audioBookUrl = audioBookUrl;
    }

    public String getTextBookURL() {
        return textBookURL;
    }

    public void setTextBookURL(String textBookURL) {
        this.textBookURL = textBookURL;
    }

    public String getBookCover() {
        return bookCover;
    }

    public void setBookCover(String bookCover) {
        this.bookCover = bookCover;
    }

    public String getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }

    public void loadCover(ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(bookCover)
                .into(imageView);
    }
}
