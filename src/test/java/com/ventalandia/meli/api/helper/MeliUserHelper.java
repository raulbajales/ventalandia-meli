package com.ventalandia.meli.api.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.service.GsonHelper;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 * 
 */
public class MeliUserHelper {

    private static final Gson gson = GsonHelper.create();

    public static MeliUser create() {
        try {
            Reader json = new FileReader(FileUtils.getDirectory("com/ventalandia/domain/meli/user/private-user.json"));
            return gson.fromJson(json, MeliUser.class);
        }
        catch (FileNotFoundException e) {
            // do nothing
        }
        return null;
    }

}
