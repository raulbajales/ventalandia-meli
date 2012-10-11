package com.ventalandia.json;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ventalandia.view.domain.NewsView;

public class ToJsonTest {

    @Test
    public void testToJson() throws Exception {
        
        NewsView newsView = new NewsView();
        newsView.setId(1004);
        newsView.setDate(new Date(1349963011745l));
        
        ObjectMapper mapper = new ObjectMapper();
        
        String json = mapper.writeValueAsString(newsView);
        String jsonExpected ="{\"id\":1004,\"buyer\":null,\"date\":\"2012-10-11T10:43:31.745\",\"type\":null,\"item\":null}";
        Assert.assertEquals(jsonExpected , json);
        
        
        
    }
    
}
