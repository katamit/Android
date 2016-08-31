package com.my.amit.mathquiz;

/**
 * Created by amit on 8/29/16.
 */
public class QuestionBank {
    private int question;
    private boolean trueQuestion;

    public QuestionBank(int question, boolean trueQuestion){
        this.question = question;
        this.trueQuestion = trueQuestion;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isTrueQuestion() {
        return trueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion) {
        this.trueQuestion = trueQuestion;
    }
}
