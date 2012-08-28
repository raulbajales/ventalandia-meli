package com.ventalandia.dao;

/**
 * 
 * @author german
 *
 * @param <T>
 */
public interface DAO<T> {

	public void persist(T t);
	
}
