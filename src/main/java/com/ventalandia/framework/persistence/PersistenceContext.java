package com.ventalandia.framework.persistence;

import javax.jdo.PersistenceManager;

/**
 * It defines a <b>point</b> where a {@link PersistenceManager} instance can be
 * associated to a {@link Thread}.
 * 
 * @author matias
 * 
 */
public class PersistenceContext {
	
	private static final ThreadLocal<PersistenceManager> threadLocal = new ThreadLocal<PersistenceManager>();
	
	public static void set(PersistenceManager pm) {
		threadLocal.set(pm);
	}

	public static void remove() {
		threadLocal.remove();
	}

	public static PersistenceManager get() {
		return threadLocal.get();		
	}

}
