package com.ventalandia.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.listener.StoreCallback;

import com.google.appengine.api.datastore.Key;

/**
 * 
 * @author matias
 * 
 */
@PersistenceCapable
public class Token implements StoreCallback {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    /**
     * It's a user Id provided by MELI.
     */
    @Persistent
    private long meliId;

    /**
     * Access token for MELI API Interaction.
     */
    @Persistent
    private String access_token;

    @Persistent
    private Long expires_in;

    /**
     * Used to get a new access_token.
     */
    @Persistent
    private String refresh_token;

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public void setMeliId(long meliId) {
        this.meliId = meliId;
    }

    public long getMeliId() {
        return meliId;
    }

    @Override
    public void jdoPreStore() {
        if (this.meliId == 0) {
            throw new RuntimeException("Invalid " + this.getClass().getSimpleName() + " state: meliId must be > 0");
        }
    }

    @Override
    public String toString() {
        return "Token [key=" + key + ", meliId=" + meliId + ", access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token=" + refresh_token + "]";
    }

}
