package com.ventalandia.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Singleton;
import com.ventalandia.api.AuthServlet;

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
            return null;
        }
    }


}
