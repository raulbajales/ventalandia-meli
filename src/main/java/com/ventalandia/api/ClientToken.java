package com.ventalandia.api;

/**
 * 
 * @author msulik
 * 
 */
public class ClientToken {

    private String vtd_token;

    public ClientToken(String hash) {
        vtd_token = hash;
    }

    public String getVtd_token() {
        return vtd_token;
    }

    public void setVtd_token(String vtd_token) {
        this.vtd_token = vtd_token;
    }

}
