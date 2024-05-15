package com.example.easylearn.learnFragment.Quiz;

import java.util.List;

public class QuizModel {
    private int color;
    private String quizNumber;
    private  int  questionNumber;

    public QuizModel(int color, String quizNumber, int questionNumber) {
        this.color = color;
        this.quizNumber = quizNumber;
        this.questionNumber = questionNumber;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(String quizNumber) {
        this.quizNumber = quizNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }
}

