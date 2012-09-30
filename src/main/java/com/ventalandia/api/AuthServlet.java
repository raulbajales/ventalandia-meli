package com.ventalandia.api;

import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.service.AuthService;

/**
 * 
 * @author matias
 * 
 */
@Singleton
public class AuthServlet extends ApiServlet {

    private static final long serialVersionUID = 6791535685445969788L;

    private static final Logger log = Logger.getLogger(AuthServlet.class.getName());
    
    private static final String TOKEN = "vtd_token";

    private static final Object EMPTY_STRING = "";

    @Inject
    private AuthService authService;

    @Inject
    private Gson gson;

    @Override
    protected Object get(HttpServletRequest req, HttpServletResponse resp) {
        return post(req, resp);
    }

    @Override
    protected Object post(HttpServletRequest req, HttpServletResponse resp) {
        String error = req.getParameter("error");
        if (error != null) {
            return new ApiError("There was an issue when you try to login: " + req.getParameter("error_description"));
        }
        else if (req.getParameter("code") != null) {
            log.info("Code from MELI: " + req.getParameter("code"));
            String hash = this.authService.generateToken(req.getParameter("code"));
            log.info("Generated hash: " + hash);
            Cookie cookie = new Cookie(TOKEN, hash);
            cookie.setMaxAge(Integer.MAX_VALUE);
            cookie.setPath("/");
            resp.addCookie(cookie);
            try {
                resp.sendRedirect("/");
            }
            catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return new ApiError(e.getMessage());
            }
        }
        return EMPTY_STRING;
    }

}
