package com.ventalandia.meli.api.notification;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author matias
 * @author german
 * 
 */
// we are not considering to persist Notifications
@Deprecated
@PersistenceCapable
public class Notification {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private long user_id;

    @Persistent
    private String resource;

    @Persistent
    private String topic;

    @Persistent
    private Date received;

    @Persistent
    private Date sent;

    @Persistent
    private boolean read;

    @Persistent
    private long sellerId;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getReceived() {
        return received;
    }

    public void setReceived(Date received) {
        this.received = received;
    }

    public Date getSent() {
        return sent;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }

    public Key getKey() {
        return key;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void markAsRead() {
        this.read = true;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    @Override
    public String toString() {
        return "Notification [key=" + key + ", user_id=" + user_id + ", resource=" + resource + ", topic=" + topic + ", received=" + received + ", sent=" + sent + ", read=" + read + ", sellerId="
                + sellerId + "]";
    }

}
