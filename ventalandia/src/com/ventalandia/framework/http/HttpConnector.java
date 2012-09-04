package com.ventalandia.framework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

/**
 * 
 * @author matias
 * 
 */
public class HttpConnector {

	public static String apiUrl = "https://api.mercadolibre.com";
	public static final String ACCESS_TOKEN = "access_token";

	public HttpResponse get(String path) {
		return get(path, new FluentStringsMap(), null);
	}

	public HttpResponse get(String path, FluentStringsMap params) {
		return get(path, params, null);
	}

	public HttpResponse get(String path, FluentStringsMap params, String body) {

		try {
			FetchOptions fetchOptions = FetchOptions.Builder.followRedirects();
			HTTPRequest request = new HTTPRequest(new URL(apiUrl + path + "?" + getQueryString(params)), HTTPMethod.GET, fetchOptions);
			HTTPHeader header = new HTTPHeader("Accept", "application/json");
			request.addHeader(header);

			HTTPResponse httpResponse = URLFetchServiceFactory.getURLFetchService().fetch(request);

			return new HttpResponse(httpResponse.getResponseCode(), new String(httpResponse.getContent()));
		} catch (MalformedURLException e) {
			throw new MeliException(e);
		} catch (IOException e) {
			throw new MeliException(e);
		}

	}

	public HttpResponse post(String path, FluentStringsMap params, String body) {
		try {

			URL url = new URL(apiUrl + path + "?" + getQueryString(params));
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.addRequestProperty("Accept", "application/json");
			connection.addRequestProperty("Content-Type", "application/json");

			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			if (!(body == null || body.equals(""))) {
				writer.write(URLEncoder.encode(body, "UTF-8"));
			}
			writer.close();

			InputStream response = connection.getInputStream();

			BufferedReader reader = new BufferedReader(new InputStreamReader(response));
			StringBuilder builder = new StringBuilder();

			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				builder.append(inputLine);
			}
			reader.close();

			return new HttpResponse(connection.getResponseCode(), builder.toString());
		} catch (MalformedURLException e) {
			throw new MeliException(e);
		} catch (IOException e) {
			throw new MeliException(e);
		}
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
