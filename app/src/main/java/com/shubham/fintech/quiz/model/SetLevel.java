package com.shubham.fintech.quiz.model;

public class SetLevel {
    private String sequence_quiz;
    private String english_title;
    private int levelNo;
    public SetLevel(String sequence, String english , int levelNo) {
        this.sequence_quiz = sequence;
        this.english_title = english;
        this.levelNo = levelNo;
    }
    public String getEnglishTitle() {
        return english_title;
    }
    public int getlock() {
        return levelNo;
    }
}
