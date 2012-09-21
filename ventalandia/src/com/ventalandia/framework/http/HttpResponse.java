package com.ventalandia.framework.http;

/**
 * 
 * @author matias
 * 
 */
// TODO Consider com.google.appengine.api.urlfetch.HTTPResponse instead of this
// class.
public class HttpResponse {

    private int responseCode;

    private String responseMessage;

    public HttpResponse(int responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

}
