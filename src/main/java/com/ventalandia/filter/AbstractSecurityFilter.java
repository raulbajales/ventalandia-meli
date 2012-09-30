package com.ventalandia.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.meli.service.MeliService;
import com.ventalandia.service.AuthService;

/**
 * 
 * @author matias
 * @author german
 * 
 */
public abstract class AbstractSecurityFilter implements Filter {

    @Inject
    private Gson gson;

    @Inject
    private MeliService meliService;

    protected FilterConfig filterConfig;

    public void destroy() {
        // do nothing
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        // casting
        this.doInnerFilter((HttpServletRequest) request, (HttpServletResponse) response, filterChain);
    }

    @Inject
    private AuthService authService;

    private void doInnerFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String hash = this.getVtdToken(request);
        Token token = this.authService.getToken(hash);

        if (token != null) {
            AuthContext.setAuthToken(token);
            this.onValidSession(filterChain, request, response);
            AuthContext.remove();
        }
        else {
            this.onInvalidSession(response);
        }
    }

    protected abstract void onInvalidSession(HttpServletResponse response);

    protected abstract void onValidSession(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response);

    private String getVtdToken(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("vtd_token")) {
                try {
                    return URLDecoder.decode(cookie.getValue(), "UTF-8");
                }
                catch (Exception e) {
                    // do nothing
                }
            }
        }
        return null;
    }

}
