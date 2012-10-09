package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;

import com.google.gson.Gson;
import com.ventalandia.domain.helper.GsonHelper;
import com.ventalandia.meli.api.notification.Notification;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 * 
 */
public class NotificationTest {

    private Gson gson = GsonHelper.create();

    @Test
    public void test() throws Exception {
        Reader json = this.getNotificationAsJson();

        Notification notification = gson.fromJson(json, Notification.class);
        assertNotNull(notification);
    }

    private Reader getNotificationAsJson() throws FileNotFoundException {
        File file = FileUtils.getDirectory("com/ventalandia/meli/api/notification/question-notification.json");
        return new FileReader(file);
    }

}