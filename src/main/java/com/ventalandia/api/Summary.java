package com.ventalandia.api;

/**
 * 
 * @author msulik
 * 
 */
public class Summary {

    private int new_questions;

    public int getNew_questions() {
        return new_questions;
    }

    public void setNew_questions(int new_questions) {
        this.new_questions = new_questions;
    }

    @Override
    public String toString() {
        return "Summary [new_questions=" + new_questions + "]";
    }

}
