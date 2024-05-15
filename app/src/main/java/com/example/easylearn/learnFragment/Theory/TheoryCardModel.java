package com.example.easylearn.learnFragment.Theory;

public class TheoryCardModel {
    private int color;
    private String theoryNumber;
    private String theoryUrl;

    public TheoryCardModel(int color, String theoryNumber, String theoryUrl) {
        this.color = color;
        this.theoryNumber = theoryNumber;
        this.theoryUrl = theoryUrl;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getTheoryNumber() {
        return theoryNumber;
    }

    public void setTheoryNumber(String theoryNumber) {
        this.theoryNumber = theoryNumber;
    }

    public String getTheoryUrl() {
        return theoryUrl;
    }

    public void setTheoryUrl(String theoryUrl) {
        this.theoryUrl = theoryUrl;
    }
}
