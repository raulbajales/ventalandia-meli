package com.ventalandia.service;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.ventalandia.domain.Question;

/**
 * 
 * @author msulik
 * 
 */
@PersistenceCapable
public class NewsFeed {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private NewsType type;

    // TODO Add order by date desc - I think we should add some index - please
    // check
    @Persistent
    private Date date;

    /**
     * Could be a {@link Question} or other stuff.
     */
    @Persistent
    private long entityId;

    /**
     * Owner.
     */
    @Persistent
    private long userId;

    @Persistent
    private long buyerId;

    @Persistent
    private String itemId;

    @Persistent
    private boolean answered;
    
    public NewsFeed() {
        this.date = new Date();
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public void setType(NewsType type) {
        this.type = type;
    }

    public NewsType getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public Long getKeyId() {
        return key != null ? key.getId() : null;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "NewsFeed [key=" + key + ", type=" + type + ", date=" + date + ", entityId=" + entityId + ", userId=" + userId + ", buyerId=" + buyerId + ", itemId=" + itemId + "]";
    }

    public void setAsNotAnswered() {
        this.answered = false;
        
    }

    public void setAsAnswered() {
        this.answered = true;
    }

}
