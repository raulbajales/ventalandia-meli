package com.ventalandia.filter;

import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import com.ventalandia.api.AuthServlet;
import com.ventalandia.view.filter.WebappSecurityFilter;

/**
 * 
 * @see AuthServlet
 * 
 * @author matias
 * @author raul
 * 
 */
@Singleton
public class ApiSecurityFilter extends AbstractSecurityFilter {

    private static final String X_VTD_TOKEN = "x-vtd-token";

    @Override
    protected void onInvalidSession(HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Override
    protected void onValidSession(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response) {
        try {
            filterChain.doFilter(request, response);
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to handle a valid session", e);
        }
    }

    protected String getVtdToken(HttpServletRequest request) {
        String hash = request.getHeader(X_VTD_TOKEN);
        if (hash != null && hash.length() > 0) {
            return hash;
        }
        else {
            return getVtdTokenFromCookie(request);
        }
    }

    protected String getVtdTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals(WebappSecurityFilter.VTD_TOKEN)) {
                try {
                    return URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                catch (Exception e) {
                    throw new RuntimeException("Unable to get token from cookie", e);
                }
            }
        }
        return null;
    }

}
