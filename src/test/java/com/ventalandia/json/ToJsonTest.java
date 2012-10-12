package com.ventalandia.json;

import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventalandia.meli.api.notification.MeliNotification;
import com.ventalandia.view.domain.NewsView;

public class ToJsonTest {

    @Test
    public void testNewsViewToJson(){
        
        NewsView newsView = new NewsView();
        newsView.setId(1004);
        newsView.setDate(new Date(1349963011745l));
        
        ObjectMapper mapper = new ObjectMapper();
        try{
            String json = mapper.writeValueAsString(newsView);
            String jsonExpected ="{\"id\":1004,\"buyer\":null,\"date\":\"2012-10-11T10:43:31.745\",\"type\":null,\"item\":null}";
            Assert.assertEquals(jsonExpected , json);
            
        }catch(Exception e){
            Assert.fail();
        }
        
    }

    
    @Test
    public void testJsonToNotification(){
        
        
        ObjectMapper mapper = new ObjectMapper();
        try{
            String jsonValue ="{\"user_id\": 1234, \"resource\": \"/questions/139876\", \"topic\": \"questions\", \"received\": \"2011-10-19T16:38:34.425Z\", \"sent\" : \"2011-10-19T16:40:34.425Z\",\"application_id\": 5678}";
            MeliNotification meliNotification = mapper.readValue(jsonValue,MeliNotification.class);
            Assert.assertEquals(1234, meliNotification.getUser_id());
            Assert.assertEquals("/questions/139876", meliNotification.getResource());
            Assert.assertEquals("questions", meliNotification.getTopic());
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
            Date expectedDatereceived = (Date) dateFormat.parseObject("2011-10-19T16:38:34.425Z");
            Date received = meliNotification.getReceived();
            
            Date expectedDatesent = (Date) dateFormat.parseObject("2011-10-19T16:40:34.425Z");
            Date sent = meliNotification.getSent();
            
            Assert.assertEquals(expectedDatereceived , received);
            Assert.assertEquals(expectedDatesent , sent);
            Assert.assertEquals("5678" , meliNotification.getApplication_id());
            
        }catch(Exception e){
            Assert.fail();
        }
        
    }
    
}
