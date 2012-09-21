package com.ventalandia.meli.service;

import com.ventalandia.domain.Token;

/**
 * 
 * @author matias
 * 
 */
public class AuthContext {

    private static final ThreadLocal<Token> threadLocal = new ThreadLocal<Token>();

    public static void setAuthToken(Token token) {
        threadLocal.set(token);
    }

    public static Token getToken() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
