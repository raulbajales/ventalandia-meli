package com.ventalandia.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.view.api.ApiError;
import com.ventalandia.view.api.ApiServlet;

/**
 * 
 * @author matias
 *
 */
@Singleton
public class AuthServlet extends ApiServlet {

	private static final long serialVersionUID = 6791535685445969788L;
	
//	@Inject
//	private MeliService meliService;

	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		return post(req, resp);
	}
	
	@Override
	protected Object post(HttpServletRequest req, HttpServletResponse resp) {
		resp.setContentType("text/plain");
		String error = req.getParameter("error");
		if (error != null) {
			ApiError apiError = new ApiError();
			apiError.setMessage("There was an issue when you try to login: " + req.getParameter("error_description"));
			return apiError;
		} else if (req.getParameter("code") != null) {
//			resp.getWriter().println("you are logged in and your code is: " + req.getParameter("code"));
			// TODO replace this, hit MELI Api to complete the flow (it needs to validate the code token)
		}
		return "";
	}
	
}
