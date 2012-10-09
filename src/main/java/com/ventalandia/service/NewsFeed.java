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

    // TODO Add order by date desc - I think we should add some index - please check
    @Persistent
    private Date date;

    /**
     * Could be a {@link Question} or other stuff.
     */
    @Persistent
    private long entityId;

    @Persistent
    private long buyerId;

    @Persistent
    private String itemId;

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

    @Override
    public String toString() {
        return "NewsFeed [key=" + key + ", type=" + type + ", date=" + date + ", entityId=" + entityId + ", buyerId=" + buyerId + ", itemId=" + itemId + "]";
    }

}
