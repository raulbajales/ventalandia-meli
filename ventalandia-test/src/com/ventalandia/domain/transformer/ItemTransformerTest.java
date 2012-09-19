package com.ventalandia.domain.transformer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ventalandia.domain.Currency;
import com.ventalandia.domain.helper.ItemHelper;
import com.ventalandia.meli.api.notification.Item;
import com.ventalandia.meli.service.CurrencyService;
import com.ventalandia.service.UserService;

/**
 * 
 * @author gzanussi
 *
 */
public class ItemTransformerTest {

    private CurrencyService currencyService;
    private UserService userService;

    @Before
    public void setup() {
        currencyService = mock(CurrencyService.class);
        userService = mock(UserService.class);

    }

    @Test
    public void testTransform() {

        Currency argentineCurrency = new Currency();
        when(currencyService.getByMeliId("ARS")).thenReturn(argentineCurrency);

        Transformer<Item, com.ventalandia.domain.Item> transformer = new ItemTransformer(currencyService,userService);
        com.ventalandia.domain.Item item = transformer.transform(ItemHelper.createItem());
        Assert.assertNotNull(item);
        Assert.assertEquals(argentineCurrency, item.getCurrency());
        Assert.assertEquals("Llantas de aleación - 15 pulgadas", item.getTitle());

    }



}
