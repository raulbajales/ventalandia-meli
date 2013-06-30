package com.ventalandia.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable
public class Question {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    @Unowned
    private User client;

    @Persistent
    @Unowned
    private User seller;

    @Persistent(dependent = "true")
    @Unowned
    private Answer answer;

    @Persistent
    @Unowned
    private Item item;

    @Persistent
    private String text;

    @Persistent
    private Date creationDate;

    @Persistent
    private String status;

    @Persistent
    private long meliId;

    @Persistent
    private boolean read;
    
    @Persistent
    private boolean close;
    
    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMeliId(long meliId) {
        this.meliId = meliId;
    }

    public long getMeliId() {
        return meliId;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Key getKey() {
        return key;
    }

    public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

	@Override
    public String toString() {
        return "Question [key=" + key + ", client=" + client + ", seller=" + seller + ", answer=" + answer + ", item=" + item + ", text=" + text + ", creationDate=" + creationDate + ", status="
                + status + ", meliId=" + meliId + ", read=" + read + "]";
    }

}
