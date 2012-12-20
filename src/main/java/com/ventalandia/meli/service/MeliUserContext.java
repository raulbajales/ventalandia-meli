package com.ventalandia.meli.service;

import com.ventalandia.meli.api.user.MeliUser;

/**
 * 
 * @author msulik
 * 
 */
public class MeliUserContext {

    private static final ThreadLocal<MeliUser> threadLocal = new ThreadLocal<MeliUser>();

    public static void set(MeliUser meliUser) {
        threadLocal.set(meliUser);
    }

    public static MeliUser get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

}
