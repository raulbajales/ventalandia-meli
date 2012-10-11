package com.ventalandia.view.domain;

public class UserView {

    private long id;

    private String nickName;

    public UserView(long id, String nickName) {
        super();
        this.id = id;
        this.nickName = nickName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
