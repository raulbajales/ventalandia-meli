package com.ventalandia.api;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author msulik
 * 
 */
@PersistenceCapable
public class Summary {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private long userId;

    @Persistent
    private int newQuestions;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getNewQuestions() {
        return newQuestions;
    }

    public void setNewQuestions(int newQuestions) {
        this.newQuestions = newQuestions;
    }

    @Override
    public String toString() {
        return "Summary [key=" + key + ", userId=" + userId + ", newQuestions=" + newQuestions + "]";
    }

    public void incNewQuestions() {
        this.newQuestions++;
    }

}
