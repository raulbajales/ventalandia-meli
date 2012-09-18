package com.ventalandia.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.domain.Token;
import com.ventalandia.meli.api.auth.AuthToken;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.service.AuthService;
import com.ventalandia.view.WebappView;

/**
 * 
 * @author matias
 * 
 */
@Singleton
public class AuthServlet extends ApiServlet {

    private static final long serialVersionUID = 6791535685445969788L;

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
            String hash = this.authService.generateToken(req.getParameter("code"));
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
