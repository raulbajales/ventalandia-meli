package com.ventalandia.view.domain;

public class UserView {

    private long id;

    private String nickname;

    public UserView(long id, String nickname) {
        super();
        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

}
