package com.ventalandia.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.ventalandia.domain.helper.GsonHelper;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 *
 */
public class MeliNotificationHelper {
    
    private static Gson gson = GsonHelper.create();

    public static MeliNotification create() {
        try {
            Reader json = new FileReader(FileUtils.getDirectory("com/ventalandia/meli/api/notification/question-notification.json"));
            return gson.fromJson(json, MeliNotification.class);
        }
        catch (FileNotFoundException e) {
            // do nothing
        }
        return null;
    }

}
