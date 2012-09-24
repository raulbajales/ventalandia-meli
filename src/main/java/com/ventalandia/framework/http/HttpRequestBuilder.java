package com.ventalandia.framework.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;

/**
 * 
 * @author matias
 * 
 */
public class HttpRequestBuilder {

    private String spec;

    private String path;

    private List<HTTPHeader> headers = new ArrayList<HTTPHeader>();

    private FluentStringsMap params = new FluentStringsMap();

    private HTTPMethod httpMethod;

    public HttpRequestBuilder(String spec) {
        this.spec = spec;
    }

    public HttpRequestBuilder withPath(String path) {
        this.path = path;
        return this;
    }

    public HttpRequestBuilder addHeader(String key, String value) {
        this.headers.add(new HTTPHeader(key, value));
        return this;
    }

    public HttpRequestBuilder addParam(String key, String value) {
        this.params.add(key, value);
        return this;
    }

    /**
     * Convenient method to add 'Accept: application/json' to the header.
     */
    public HttpRequestBuilder acceptJson() {
        return this.addHeader("Accept", "application/json");
    }

    public HttpRequestBuilder get() {
        this.httpMethod = HTTPMethod.GET;
        return this;
    }

    public HttpRequestBuilder post() {
        this.httpMethod = HTTPMethod.POST;
        return this;
    }

    public HttpRequestBuilder delete() {
        this.httpMethod = HTTPMethod.DELETE;
        return this;
    }

    public HttpRequestBuilder put() {
        this.httpMethod = HTTPMethod.PUT;
        return this;
    }

    public HttpRequestBuilder head() {
        this.httpMethod = HTTPMethod.HEAD;
        return this;
    }

    /**
     * Creates a new instance of a {@link HTTPRequest}. Every time you invoke
     * this method you get a new Instance.
     * 
     * @return a new instance of {@link HTTPRequest}
     */
    public HTTPRequest build() {
        try {
            FetchOptions fetchOptions = FetchOptions.Builder.followRedirects();
            HTTPRequest request = new HTTPRequest(new URL(this.createSpec()), this.httpMethod, fetchOptions);
            for (HTTPHeader header : this.headers) {
                request.addHeader(header);
            }
            return request;
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private String createSpec() {
        StringBuilder builder = new StringBuilder();

        if (this.spec == null || this.spec.length() == 0) {
            throw new RuntimeException("Invalid spec (URL) definition.");
        }

        builder.append(this.spec);

        if (this.path != null && this.path.length() > 0) {
            builder.append(this.path + "?");
            if (this.params.size() > 0) {
                builder.append(getQueryString(this.params));
            }
        }

        return builder.toString();
    }

    public static String getQueryString(FluentStringsMap params) {
        StringBuilder result = new StringBuilder();

        for (Iterator<Entry<String, List<String>>> iterator = params.iterator(); iterator.hasNext();) {
            Entry<String, List<String>> entry = iterator.next();

            StringBuilder builder = new StringBuilder();
            for (Iterator<String> valueIterator = entry.getValue().iterator(); valueIterator.hasNext();) {
                String value = valueIterator.next();
                builder.append(value);
                if (valueIterator.hasNext()) {
                    builder.append(",");
                }
            }
            result.append(String.format("%s=%s", entry.getKey(), builder.toString()));
            if (iterator.hasNext()) {
                result.append("&");
            }
        }

        return result.toString();
    }

}
