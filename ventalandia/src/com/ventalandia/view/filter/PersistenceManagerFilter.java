package com.ventalandia.view.filter;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

/**
 * Simple implementation of a {@link Filter}. This ensure a
 * {@link PersistenceManager} instances for every request. This is because every
 * request will use some persistence support to execute business logic.
 * 
 * @author matias
 * 
 */
@Singleton
public class PersistenceManagerFilter implements Filter {
	
	@Inject
	private PersistenceManagerProvider persistenceManagerProvider;

	@Override
	public void destroy() {
		// do nothing
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) 
	throws IOException, ServletException {
		PersistenceManager pm = this.persistenceManagerProvider.get();
		
        try {
            filterChain.doFilter(request, response);
            this.persistenceManagerProvider.remove();
        } finally {
        	pm.close();
        }
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// do nothing
	}

}
