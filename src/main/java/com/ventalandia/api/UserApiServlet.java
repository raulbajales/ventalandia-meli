package com.ventalandia.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.meli.service.MeliUserContext;
import com.ventalandia.meli.service.UserMeliService;

/**
 * 
 * @author msulik
 *
 */
@Singleton
@Deprecated
public class UserApiServlet extends ApiServlet {

	private static final long serialVersionUID = 3490358649836139664L;
	
	@Inject
	private UserService userService;
	
	@Inject
	private UserMeliService userMeliService;


	// TODO add memcache or something to this call!!!
	@Override
	protected Object get(HttpServletRequest req, HttpServletResponse resp) {
		MeliUserContext.set(this.userMeliService.getCurrentUser());
		
		return this.userService.getCurrent();
	}
}
