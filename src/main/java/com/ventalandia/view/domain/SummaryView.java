package com.ventalandia.view.domain;

import com.ventalandia.api.Summary;

public class SummaryView {

    private int new_questions;

    private long user_id;

    public SummaryView(Summary summary) {
        this.new_questions = summary.getNewQuestions();
        this.user_id = summary.getUserId();
    }

    public long getUser_id() {
        return user_id;
    }

    public int getNew_questions() {
        return new_questions;
    }

}
