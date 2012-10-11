package com.ventalandia.meli.api.notification;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class UserFrom {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private long id;

    @Persistent
    private long answered_questions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAnswered_questions() {
        return answered_questions;
    }

    public void setAnswered_questions(long answered_questions) {
        this.answered_questions = answered_questions;
    }

    @Override
    public String toString() {
        return "UserFrom [key=" + key + ", id=" + id + ", answered_questions=" + answered_questions + "]";
    }

}
