package com.ventalandia.view.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ventalandia.framework.json.CustomJsonDateSerializer;
import com.ventalandia.service.NewsType;

/**
 * 
 * @author german
 * 
 */
public class NewsView {

    private long id;

    private SimpleUserView buyer;

    @JsonSerialize(using = CustomJsonDateSerializer.class)
    private Date date;

    private NewsType type;

    private ItemView item;

    private long entityId;
    
    private boolean answered;

    private boolean close;
    
    public NewsView() {
        super();

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SimpleUserView getBuyer() {
        return buyer;
    }

    public void setBuyer(SimpleUserView buyer) {
        this.buyer = buyer;
    }

    public ItemView getItem() {
        return item;
    }

    public void setItem(ItemView item) {
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NewsType getType() {
        return type;
    }

    public void setType(NewsType type) {
        this.type = type;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public long getEntityId() {
        return entityId;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

	public boolean isClose() {
		return close;
	}

	public void setClose(boolean close) {
		this.close = close;
	}

    
}