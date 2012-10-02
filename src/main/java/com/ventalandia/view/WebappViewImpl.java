package com.ventalandia.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.view.filter.WebappSecurityFilter;

public class WebappViewImpl implements WebappView {

    @Inject
    private Gson gson;

	public void renderHome(HttpServletResponse response, ServletContext servletContext) {
		try {
            String theToken = this.gson.toJson(AuthContext.getToken());
            Map<String, String> params = new HashMap<String, String>();
            params.put(WebappSecurityFilter.VTD_TOKEN, theToken);
			this.render(servletContext.getResourceAsStream("/home.tmpl"), response, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void renderGuest(HttpServletResponse response, ServletContext servletContext) {
		try {
			this.render(servletContext.getResourceAsStream("/guest.tmpl"), response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void render(InputStream resourceAsStream,
			HttpServletResponse response) throws IOException {
        this.render(resourceAsStream, response, new HashMap<String, String>());
	}

	private void render(InputStream resourceAsStream,
			HttpServletResponse response, Map<String, String> params) throws IOException {
//		String tmpl = fillTemplate(resourceAsStream, params);
		String tmpl = (new Scanner(resourceAsStream)).useDelimiter("\\A").next();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		
		response.getWriter().print(tmpl);
		response.getWriter().flush();
	}

	private String fillTemplate(InputStream resourceAsStream,
			Map<String, String> params) {
		String keyPrefix = "{$";
		String keySufix = "}";
		String tmpl = (new Scanner(resourceAsStream)).useDelimiter("\\A").next();
		for (String key : params.keySet()) {
			String search = keyPrefix + key + keySufix;
			tmpl = tmpl.replaceAll(search, params.get(key));
		}
		return tmpl;
	}

}
