package com.ventalandia.view;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public interface WebappView {

	void renderHome(HttpServletResponse resp, ServletContext servletContext);

	void renderGuest(HttpServletResponse response, ServletContext servletContext);

}
