package com.ventalandia.framework.http;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;
import com.ventalandia.domain.meli.DomainTest;

/**
 * 
 * @author matias
 * 
 */
public class HttpRequestBuilderTest extends DomainTest {

    private static final String URI = "https://api.mercadolibre.com/questions/2455498075";

    @Inject
    private URLFetchService urlFetchService;

    @Test
    public void build() throws IOException {
        HttpRequestBuilder builder = new HttpRequestBuilder(URI).get().acceptJson();
        HTTPResponse responseOk = this.urlFetchService.fetch(builder.build());

        String content = new String(responseOk.getContent());

        Assert.assertEquals(200, responseOk.getResponseCode());
        Assert.assertTrue(content.contains("id"));
        Assert.assertTrue(content.contains("answer"));

        builder.post();
        HTTPResponse responseNotOk = this.urlFetchService.fetch(builder.build());

        Assert.assertEquals(403, responseNotOk.getResponseCode());
    }
    
    @Test
    public void getQueryString() {
        FluentStringsMap params = new FluentStringsMap();
        
        params.add("key1", "value1");
        params.add("key2", "value2");

        Assert.assertEquals("key1=value1&key2=value2", HttpRequestBuilder.getQueryString(params));
        
        params.add("key2", "value3");

        Assert.assertEquals("key1=value1&key2=value2,value3", HttpRequestBuilder.getQueryString(params));
    }

}
