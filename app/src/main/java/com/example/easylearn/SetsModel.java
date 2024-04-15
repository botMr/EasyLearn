package com.example.easylearn;

public class SetsModel {

    private int setBookName;
    private int setAuthorName;
    private String audioURL;
    private String audioBookName;
    private String textBookURL;
    private int setBookInfo;
    private int color;

    public SetsModel(int setBookName, int setAuthorName, String audioURL, String audioBookName, String textBookURL, int setBookInfo, int color) {
        this.setBookName = setBookName;
        this.setAuthorName = setAuthorName;
        this.audioURL = audioURL;
        this.audioBookName = audioBookName;
        this.textBookURL = textBookURL;
        this.setBookInfo = setBookInfo;
        this.color = color;
    }

    public int getSetBookName() {
        return setBookName;
    }

    public void setSetBookName(int setBookName) {
        this.setBookName = setBookName;
    }

    public int getSetAuthorName() {
        return setAuthorName;
    }

    public void setSetAuthorName(int setAuthorName) {
        this.setAuthorName = setAuthorName;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public String getAudioBookName() {
        return audioBookName;
    }

    public void setAudioBookName(String audioBookName) {
        this.audioBookName = audioBookName;
    }

    public String getTextBookURL() {
        return textBookURL;
    }

    public void setTextBookURL(String textBookURL) {
        this.textBookURL = textBookURL;
    }

    public int getSetBookInfo() {
        return setBookInfo;
    }

    public void setSetBookInfo(int setBookInfo) {
        this.setBookInfo = setBookInfo;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

}
