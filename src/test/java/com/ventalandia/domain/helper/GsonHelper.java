package com.ventalandia.domain.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author msulik
 *
 */
public class GsonHelper {

    public static Gson create() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
    }

}
