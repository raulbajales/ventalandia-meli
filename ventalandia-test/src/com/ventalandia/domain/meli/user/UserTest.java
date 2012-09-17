package com.ventalandia.domain.meli.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.meli.DomainTest;
import com.ventalandia.meli.api.user.MeliUser;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author matias
 * 
 */
public class UserTest extends DomainTest {

    @Inject
    private Gson gson;

    @Test
    public void test() throws FileNotFoundException {
        Reader json = this.getUserAsJson();
        MeliUser user = this.gson.fromJson(json, MeliUser.class);

        System.out.println(user);
    }

    private Reader getUserAsJson() throws FileNotFoundException {
        File file = FileUtils.getDirectory("com/ventalandia/domain/meli/user/private-user.json");
        return new FileReader(file);
    }

}
