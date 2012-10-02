package com.ventalandia.api;

import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.service.AuthService;
import com.ventalandia.view.WebappView;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * 
 * @author matias
 * 
 */
@Singleton
public class AuthServlet extends ApiServlet {

    private static final long serialVersionUID = 6791535685445969788L;

    private static final Logger LOGGER = Logger.getLogger(AuthServlet.class.getName());

    private static final Object EMPTY_STRING = "";

	@Inject
	private WebappView webappView;

    @Inject
    private AuthService authService;

    @Inject
    private Gson gson;

    @Override
    protected Object get(HttpServletRequest req, HttpServletResponse resp) {
        return process(req, resp);
    }
    
    @Override
    protected Object post(HttpServletRequest req, HttpServletResponse resp) {
        return process(req, resp);
    }

    protected Object process(HttpServletRequest req, HttpServletResponse resp) {
        String error = req.getParameter("error");
        if (error != null) {
            return new ApiError("There was an issue when you try to login: " + req.getParameter("error_description"));
        }
        else if (req.getParameter("code") != null) {
            LOGGER.info("Code from MELI: " + req.getParameter("code"));
            String hash = this.authService.generateToken(req.getParameter("code"));
            LOGGER.info("Generated hash: " + hash);

            try {
//                Cookie cookie = new Cookie(WebappSecurityFilter.VTD_TOKEN, hash);
//                cookie.setMaxAge(Integer.MAX_VALUE);
//                cookie.setPath("/");
//                resp.addCookie(cookie);
//                resp.sendRedirect("/");
                
                String theCookie = WebappSecurityFilter.VTD_TOKEN + "=" + hash + "; expires=" + (new Date(0)).getTime();
                LOGGER.info("Setting header Set-Cookie to: " + theCookie);
                resp.addHeader("Set-Cookie", theCookie);
                resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                resp.addHeader("Location", "/");
                resp.flushBuffer();
            }            
            catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new ApiError(e.getMessage());
            }
        }
        return EMPTY_STRING;
    }

}
