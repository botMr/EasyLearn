package com.example.easylearn.accountFragment;

public class Achievement {
    private String id;
    private String text;
    private int imageResource;
    private boolean isCompleted;

    public Achievement(String id, String text, int imageResource, boolean isCompleted) {
        this.id = id;
        this.text = text;
        this.imageResource = imageResource;
        this.isCompleted = isCompleted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
