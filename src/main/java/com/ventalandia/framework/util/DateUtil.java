package com.ventalandia.framework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public final class DateUtil {
    
    private static final Logger LOGGER = Logger.getLogger(DateUtil.class.getName());
    
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static final String format(Date date){
        return dateFormat.format(date);
    }

    public static Date parse(String date) {
 
        try {
            return dateFormat.parse(date);
        }
        catch (ParseException e) {
            LOGGER.info("Incorrect format");
        }
        return null;
        
    }
    
}
