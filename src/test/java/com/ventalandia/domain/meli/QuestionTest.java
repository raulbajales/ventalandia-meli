package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.junit.Test;

import com.google.gson.Gson;
import com.ventalandia.domain.helper.GsonHelper;
import com.ventalandia.meli.api.notification.Question;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 *
 */
public class QuestionTest {

    private Gson gson = GsonHelper.create();

    @Test
    public void testNotificationContent() throws Exception {
        Reader json = this.getQuestionAsJson();
        Question notificationContent = gson.fromJson(json, Question.class);
        assertNotNull(notificationContent);
        assertNotNull(notificationContent.getFrom());
        assertEquals(79450083, notificationContent.getFrom().getId());
    }

    private Reader getQuestionAsJson() throws FileNotFoundException {
        File file = FileUtils.getDirectory("com/ventalandia/meli/api/question/question.json");
        return new FileReader(file);
    }

}
