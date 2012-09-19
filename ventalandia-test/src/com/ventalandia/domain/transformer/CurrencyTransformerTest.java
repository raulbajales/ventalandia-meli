package com.ventalandia.domain.transformer;

import junit.framework.Assert;

import org.junit.Test;

import com.ventalandia.meli.api.notification.Currency;

public class CurrencyTransformerTest {
    
    
    @Test
    public void testTransformer() throws Exception {
        
        Transformer<Currency,com.ventalandia.domain.Currency> transformer = new CurrencyTransformer();
        com.ventalandia.domain.Currency currency = transformer.transform(createCurrency());
        Assert.assertNotNull(currency);
        
        Assert.assertEquals("ARS", currency.getMeliId());
        Assert.assertEquals("Peso argentino",currency.getDescription());
        Assert.assertEquals("$", currency.getSymbol());
        Assert.assertEquals(2,currency.getDecimalPlaces());
                
        
    }

    private Currency createCurrency() {
        Currency currency = new Currency();
        currency.setId("ARS");
        currency.setDecimal_places(2);
        currency.setDescription("Peso argentino");
        currency.setSymbol("$");
        return currency;
    }

}
