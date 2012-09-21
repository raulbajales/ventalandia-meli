package com.ventalandia.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public class WebappViewImpl implements WebappView {

	public void renderHome(HttpServletResponse response, ServletContext servletContext) {
		try {
			this.render(servletContext.getResourceAsStream("/home.tmpl"), response);
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
		String tmpl = (new Scanner(resourceAsStream)).useDelimiter("\\A").next();
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");		
		response.getWriter().print(tmpl);
		response.getWriter().flush();
	}

}
