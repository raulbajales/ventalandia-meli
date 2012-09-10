package com.ventalandia.domain.meli.user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.net.URISyntaxException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ventalandia.meli.api.user.MeliPublicUser;

/**
 * 
 * @author matias
 *
 */
public class PublicUserTest {
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
	
	@Test
	public void test() throws FileNotFoundException {
		Reader json = this.getUserAsJson();
		MeliPublicUser user = this.gson.fromJson(json, MeliPublicUser.class);
		
		System.out.println(user);
	}

	private Reader getUserAsJson() throws FileNotFoundException {
		File file = getDirectory("com/ventalandia/domain/meli/user/public-user.json");
		return new FileReader(file);
	}

    private static File getDirectory(String simpleCasePath) {
        try {
            return new File(ClassLoader.getSystemClassLoader().getResource(simpleCasePath).toURI());
        } catch (URISyntaxException e1) {
            throw new RuntimeException("Problems when it tries to load the files.", e1);
        }
    }

}
