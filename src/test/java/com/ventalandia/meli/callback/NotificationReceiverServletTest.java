package com.ventalandia.meli.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.google.inject.Inject;
import com.ventalandia.service.MeliDomainTest;
import com.ventalandia.util.file.FileUtils;

/**
 * 
 * @author msulik
 * 
 */
public class NotificationReceiverServletTest extends MeliDomainTest {

    @Inject
    private NotificationReceiverServlet servlet;

    @Test
    public void test() throws Exception {
        HttpServletRequest req = mock(HttpServletRequest.class);
        when(req.getReader()).thenReturn(this.create());

        HttpServletResponse resp = mock(HttpServletResponse.class);
        servlet.post(req, resp);
    }

    private Reader getNotificationAsJson() throws FileNotFoundException {
        File file = FileUtils.getDirectory("com/ventalandia/meli/api/notification/question-notification.json");
        return new FileReader(file);
    }

    private BufferedReader create() throws FileNotFoundException {
        Reader in = getNotificationAsJson();
        return new BufferedReader(in);
    }

}
