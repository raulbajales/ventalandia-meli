package com.ventalandia.api;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author msulik
 * 
 */
public class EmailServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = Logger.getLogger(EmailServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        // LOGGER.info("sendTo param: " + sendTo);
        Properties props = new Properties();

        Session email = Session.getDefaultInstance(props, null);

        try {
            MimeMessage message = new MimeMessage(email, request.getInputStream());
            String summary = message.getSubject();
            String description = getText(message);
            Address[] addresses = message.getFrom();

            LOGGER.info("Subject: " + summary);
            LOGGER.info("From: " + addresses[0].toString());
            LOGGER.info("Message: " + description);
        }
        catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage());
        }
    }

    private String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String) p.getContent();
            // textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart) p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                }
                else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                }
                else {
                    return getText(bp);
                }
            }
            return text;
        }
        else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }
}
