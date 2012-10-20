package com.ventalandia.api;

/**
 * 
 * @author msulik
 * 
 */
public class AnswerApi {

    private long question_id;

    private String text;

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "AnswerApi [question_id=" + question_id + ", text=" + text + "]";
    }

}
