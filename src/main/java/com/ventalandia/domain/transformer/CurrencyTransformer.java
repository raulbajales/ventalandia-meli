package com.ventalandia.domain.transformer;

import com.ventalandia.domain.Currency;



/**
 * 
 * @author matias
 *
 */
public class CurrencyTransformer implements Transformer<com.ventalandia.meli.api.notification.Currency, Currency> {

    public Currency transform(com.ventalandia.meli.api.notification.Currency in) {
        
        Currency currency = new Currency();
        currency.setDecimalPlaces(in.getDecimal_places());
        currency.setDescription(in.getDescription());
        currency.setSymbol(in.getSymbol());
        currency.setMeliId(in.getId());
        
        return currency;
    }

}
