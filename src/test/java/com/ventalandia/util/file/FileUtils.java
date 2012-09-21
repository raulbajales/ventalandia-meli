package com.ventalandia.util.file;

import java.io.File;
import java.net.URISyntaxException;

/**
 * 
 * @author matias
 * 
 */
public class FileUtils {

    public static File getDirectory(String simpleCasePath) {
        try {
            return new File(ClassLoader.getSystemClassLoader().getResource(simpleCasePath).toURI());
        }
        catch (URISyntaxException e1) {
            throw new RuntimeException("Problems when it tries to load the files.", e1);
        }
    }

}
