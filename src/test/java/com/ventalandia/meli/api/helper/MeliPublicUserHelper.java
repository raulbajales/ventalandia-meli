package com.ventalandia.meli.api.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import com.google.gson.Gson;
import com.ventalandia.domain.helper.GsonHelper;
import com.ventalandia.meli.api.user.MeliPublicUser;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 * 
 */
public class MeliPublicUserHelper {

    private static final Gson gson = GsonHelper.create();

    public static final long USER_ID = 90661434;

    public static MeliPublicUser create() {
        try {
            Reader json = new FileReader(FileUtils.getDirectory("com/ventalandia/domain/meli/user/public-user.json"));
            return gson.fromJson(json, MeliPublicUser.class);
        }
        catch (FileNotFoundException e) {
            // do nothing
        }
        return null;
    }

}
