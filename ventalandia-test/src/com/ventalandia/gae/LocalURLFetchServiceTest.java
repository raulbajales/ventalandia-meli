package com.ventalandia.gae;

import java.net.URL;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.appengine.tools.development.testing.LocalURLFetchServiceTestConfig;

/**
 * 
 * @author matias
 *
 */
public class LocalURLFetchServiceTest {
	
	private static final Logger LOGGER = Logger.getLogger(LocalURLFetchServiceTest.class.getName());

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalURLFetchServiceTestConfig());
	
	@Before
	public void before() {
		helper.setUp();
	}
	
	@After
	public void after() {
		helper.tearDown();
	}
	
	@Test
	public void test() throws Exception {
		URLFetchService urlFetchService = URLFetchServiceFactory.getURLFetchService();
		
		HTTPRequest request = new HTTPRequest(new URL("http://www.google.com"), HTTPMethod.GET);
		HTTPResponse response = urlFetchService.fetch(request);
		
		Assert.assertEquals(200, response.getResponseCode());
		Assert.assertTrue(response.getContent().length > 0);
		
		LOGGER.info("HTTP Code : " + response.getResponseCode());
		LOGGER.info("Content : " + new String(response.getContent()));
	}
	
}


