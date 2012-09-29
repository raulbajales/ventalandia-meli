package com.ventalandia.framework.http;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.inject.Inject;

/**
 * 
 * @author matias
 * 
 */
public class HttpConnector {

    public static String apiUrl = "https://api.mercadolibre.com";

    private static final String APPLICATION_JSON = "application/json";

    @Inject
    private URLFetchService urlFetchService;

    public HttpResponse get(String path) {
        return get(path, new FluentStringsMap(), null);
    }

    public HttpResponse get(String path, FluentStringsMap params) {
        return get(path, params, null);
    }
    
    public HTTPResponse execute(HTTPRequest httpRequest) {
        try {
            return this.urlFetchService.fetch(httpRequest);
        }
        catch (IOException e) {
            throw new RuntimeException("Error executing an HTTP call: " + httpRequest.getURL(), e);
        }
    }

    // TODO add body to the request
    public HttpResponse get(String path, FluentStringsMap params, String body) {
        try {
            FetchOptions fetchOptions = FetchOptions.Builder.followRedirects();
            HTTPRequest request = new HTTPRequest(new URL(apiUrl + path + "?" + HttpRequestBuilder.getQueryString(params)), HTTPMethod.GET, fetchOptions);
            HTTPHeader header = new HTTPHeader("Accept", APPLICATION_JSON);
            request.addHeader(header);

            HTTPResponse httpResponse = this.urlFetchService.fetch(request);

            return new HttpResponse(httpResponse.getResponseCode(), new String(httpResponse.getContent()));
        }
        catch (MalformedURLException e) {
            throw new MeliException(e);
        }
        catch (IOException e) {
            throw new MeliException(e);
        }
    }

    public HttpResponse post(String path, FluentStringsMap params) {
        return this.post(path, params, null);
    }
    
    public HttpResponse post(String path, FluentStringsMap params, String body) {
        try {
            FetchOptions fetchOptions = FetchOptions.Builder.followRedirects();
            HTTPRequest request = new HTTPRequest(new URL(apiUrl + path + "?" + HttpRequestBuilder.getQueryString(params)), HTTPMethod.POST, fetchOptions);

            if (body != null && body.length() > 0) {
                request.setPayload(body.getBytes());
            }

            HTTPHeader header = new HTTPHeader("Accept", APPLICATION_JSON);
            request.addHeader(header);

            HTTPResponse httpResponse = this.urlFetchService.fetch(request);

            return new HttpResponse(httpResponse.getResponseCode(), new String(httpResponse.getContent()));
        }
        catch (MalformedURLException e) {
            throw new MeliException(e);
        }
        catch (IOException e) {
            throw new MeliException(e);
        }
    }

}
