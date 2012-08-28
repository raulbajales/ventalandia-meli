package com.ventalandia.dao.impl;

import javax.jdo.PersistenceManagerFactory;

import com.ventalandia.dao.DAO;

/**
 * 
 * @author german
 *
 * @param <T>
 */
public abstract class AbstractDAO<T> implements DAO<T>{

	private PersistenceManagerFactory pmf;
	
	
	public AbstractDAO(PersistenceManagerFactory pmf) {
		this.pmf = pmf;
	}


	@Override
	public void persist(T t){
		pmf.getPersistenceManager().makePersistent(t);
	}
	
}
