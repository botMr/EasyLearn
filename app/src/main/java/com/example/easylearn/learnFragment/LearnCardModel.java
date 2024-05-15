package com.example.easylearn.learnFragment;

public class LearnCardModel {
    private int color;
    private String cardSubject;
    private String itemCount;
    private String learnCardText;
    private String adapterName;

    public LearnCardModel(int color, String cardSubject, String itemCount, String learnCardText, String adapterName) {
        this.color = color;
        this.cardSubject = cardSubject;
        this.itemCount = itemCount;
        this.learnCardText = learnCardText;
        this.adapterName = adapterName;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getCardSubject() {
        return cardSubject;
    }

    public void setCardSubject(String cardSubject) {
        this.cardSubject = cardSubject;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getLearnCardText() {
        return learnCardText;
    }

    public void setLearnCardText(String learnCardText) {
        this.learnCardText = learnCardText;
    }

    public String getAdapterName() {
        return adapterName;
    }

    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }
}
