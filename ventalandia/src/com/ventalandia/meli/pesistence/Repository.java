package com.ventalandia.meli.pesistence;

import java.util.List;
import java.util.Map;

public interface Repository<T> {
	
    public void add(T t);
    
	public T get(Long id);
	
	public List<T> search(Map<String, Object> params);
	
}
