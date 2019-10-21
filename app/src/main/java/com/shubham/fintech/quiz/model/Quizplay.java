package com.shubham.fintech.quiz.model;

import java.util.ArrayList;

public class Quizplay {
	private String question;
	private ArrayList<String> options = new ArrayList<String>();
	private String trueAns;
	public Quizplay(String question) {
		super();
		this.question = question;
	}
	public String getQuestion() {
		return question;
	}
	public boolean addOption(String option) {
		return this.options.add(option);
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public String getTrueAns() {
		return trueAns;
	}
	public void setTrueAns(String trueAns) {
		this.trueAns = trueAns;
	}
	@Override
	public String toString() {
		return "Question: " + question + " OptionS: " + options;
	}

}
