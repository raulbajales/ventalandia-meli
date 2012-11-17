package com.ventalandia.domain;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.datanucleus.annotations.Unowned;

/**
 * @author gzanussi
 * 
 */
@PersistenceCapable
public class User implements Comparable<User>{

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private long meliId;

    @Persistent
    private String nickName;

    @Persistent
    @Unowned
    private Country country;

    @Persistent
    private Date registrationDate;
    
    @Persistent
    private String pictureUrl;

    public long getMeliId() {
        return meliId;
    }

    public void setMeliId(long meliId) {
        this.meliId = meliId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

	public Key getKey() {
		return key;
	}

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    @Override
    public int compareTo(User o) {
        return this.key.compareTo(o.key);
    }
    

}
