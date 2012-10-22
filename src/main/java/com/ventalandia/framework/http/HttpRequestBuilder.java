package com.ventalandia.framework.http;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

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

    /**
     * It's a host. Must not be null.
     */
    private String spec;

    /**
     * Path to a resource.
     */
    private String path;

    /**
     * Header parameters.
     */
    private List<HTTPHeader> headers = new ArrayList<HTTPHeader>();

    /**
     * Query string stuff.
     */
    private FluentStringsMap params = new FluentStringsMap();

    private HTTPMethod httpMethod;

    private String body;

    public HttpRequestBuilder(String spec) {
        this.spec = spec.endsWith("/") ? spec : spec + "/";
    }

    public HttpRequestBuilder withPath(String path) {
        this.path = path.startsWith("/") ? path.substring(1) : path;
        return this;
    }

    public HttpRequestBuilder body(String body) {
        this.body = body;
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

    public HttpRequestBuilder replaceParam(String key, String value) {
        this.params.replace(key, value);
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

    private static final Logger LOGGER = Logger.getLogger(HttpRequestBuilder.class.getName());

    /**
     * Creates a new instance of a {@link HTTPRequest}. Every time you invoke
     * this method you get a new Instance.
     * 
     * @return a new instance of {@link HTTPRequest}
     */
    public HTTPRequest build() {
        try {
            FetchOptions fetchOptions = FetchOptions.Builder.withDefaults();
            fetchOptions.doNotValidateCertificate();
            fetchOptions.setDeadline(60D);

            HTTPRequest request = new HTTPRequest(new URL(this.createSpec()), this.httpMethod, fetchOptions);

            for (HTTPHeader header : this.headers) {
                request.addHeader(header);
            }

            if (this.body != null && this.body.length() > 0) {
                LOGGER.info("setting body " + this.body);
                request.setPayload(this.body.getBytes());
            }

            return request;
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a full url (uri + path + query string) based on previously
     * defined options.</br>
     * 
     * This definition follows this format: http://spect/path?params where
     * parameters it's a pair of key=value option.
     * 
     */
    private String createSpec() {
        StringBuilder builder = new StringBuilder();

        if (this.spec == null || this.spec.length() == 0) {
            throw new RuntimeException("Invalid spec (URL) definition.");
        }

        builder.append(this.spec);

        if (this.path != null && this.path.length() > 0) {
            builder.append(this.path);
            if (this.params.size() > 0) {
                builder.append("?");
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

    public boolean containsParam(String key) {
        return this.params.containsKey(key);
    }

    @Override
    public String toString() {
        return "HttpRequestBuilder [spec=" + spec + ", path=" + path + ", headers=" + headers + ", params=" + params + ", httpMethod=" + httpMethod + ", body=" + body + "]";
    }

}
