package com.shubham.fintech.quiz.model;

import java.util.ArrayList;

public class Review {

    private String question, rightAns, wrongAns;
    public ArrayList<String> optionList;

    public Review() {
    }

    public Review(String question, String rightAns, String wrongAns, ArrayList<String> optionList) {
        this.question = question;
        this.rightAns = rightAns;
        this.wrongAns = wrongAns;
        this.optionList = optionList;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }

    public String getWrongAns() {
        return wrongAns;
    }

    public void setWrongAns(String wrongAns) {
        this.wrongAns = wrongAns;
    }

    public ArrayList<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(ArrayList<String> optionList) {
        this.optionList = optionList;
    }
}
