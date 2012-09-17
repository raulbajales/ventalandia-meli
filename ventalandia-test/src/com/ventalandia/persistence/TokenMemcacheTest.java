package com.ventalandia.persistence;

import junit.framework.Assert;

import org.junit.Test;

import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.ventalandia.domain.meli.DomainTest;

/**
 * 
 * @author matias
 * 
 */
public class TokenMemcacheTest extends DomainTest {

    private static final String VALUE = "value";

    private static final String KEY = "key";

    @Test
    public void test() throws InterruptedException {
        MemcacheService service = MemcacheServiceFactory.getMemcacheService();

        service.put(KEY, VALUE, Expiration.byDeltaSeconds(2));

        Assert.assertEquals(VALUE, service.get(KEY));

        Thread.sleep(2100);

        Assert.assertNull(service.get(KEY));
    }

}
