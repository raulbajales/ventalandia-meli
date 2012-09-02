package com.ventalandia.domain.meli;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.framework.http.FluentStringsMap;
import com.ventalandia.framework.http.HttpConnector;

/**
 * 
 * @author matias
 *
 */
public class HttpConnectorTest {

	@Test
	public void getQueryString() {
		FluentStringsMap params = new FluentStringsMap();
		
		params.add("key1", "value1");
		params.add("key2", "value2");

		Assert.assertEquals("key1=value1&key2=value2", HttpConnector.getQueryString(params));
		
		params.add("key2", "value3");

		Assert.assertEquals("key1=value1&key2=value2,value3", HttpConnector.getQueryString(params));
	}

}
