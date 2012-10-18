package com.ventalandia.framework.util;

import java.util.HashMap;

public  final class MapBuilder {
	
	public static final synchronized CustomMap<String,Object> build(){
		return new CustomMap<String, Object>();
	}

	@SuppressWarnings("serial")
	public static class CustomMap<K,V> extends HashMap<K, V>{
		
		public CustomMap<K,V> putValue(K key, V value) {
			 super.put(key, value);
			 return this;
		}
		
	}
	
}
