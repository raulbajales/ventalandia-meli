package com.ventalandia.filter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.ventalandia.domain.Token;
import com.ventalandia.meli.service.AuthContext;
import com.ventalandia.service.AuthService;

/**
 * 
 * @author matias
 * @author german
 * 
 */
public abstract class AbstractSecurityFilter implements Filter {

    private static final Logger log = Logger.getLogger(AbstractSecurityFilter.class.getName());

    private static final String X_VTD_TOKEN = "x-vtd-token";

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
        log.info("Hash from request: " + hash);

        Token token = this.authService.getToken(hash);

        if (token != null) {
            log.info("Token from previous hash is: " + token.toString());
            AuthContext.setAuthToken(token);
            this.onValidSession(filterChain, request, response);
            AuthContext.remove();
        }
        else {
            log.info("There is no token for the previous hash!");
            this.onInvalidSession(response);
        }
    }

    protected abstract void onInvalidSession(HttpServletResponse response);

    protected abstract void onValidSession(FilterChain filterChain, HttpServletRequest request, HttpServletResponse response);

    private String getVtdToken(HttpServletRequest request) {
        String hash = request.getHeader(X_VTD_TOKEN);
        if (hash != null && hash.length() > 0) {
            return hash;
        }
        else {
            return null;
        }
    }

}
