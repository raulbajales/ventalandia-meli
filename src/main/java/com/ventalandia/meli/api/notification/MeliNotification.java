package com.ventalandia.meli.api.notification;

import java.util.Date;

/**
 * It's a notification received from MELI.
 * 
 * @author msulik
 * 
 */
public class MeliNotification {

    private long user_id;

    private String resource;

    /**
     * Inform the relation of the notification. It could be: orders, items, questions.
     */
    private String topic;

    private Date received;

    private Date sent;

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

    @Override
    public String toString() {
        return "MeliNotification [user_id=" + user_id + ", resource=" + resource + ", topic=" + topic + ", received=" + received + ", sent=" + sent + "]";
    }

}
