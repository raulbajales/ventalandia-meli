package com.ventalandia.service;

import com.ventalandia.domain.Currency;
import com.ventalandia.domain.transformer.CurrencyTransformer;
import com.ventalandia.meli.pesistence.CurrencyRepository;
import com.ventalandia.meli.service.MeliService;

public class CurrencyServiceImpl implements CurrencyService{

    private CurrencyRepository currencyRepository;
    private CurrencyTransformer currencyTransformer;
    private MeliService meliService;
    
    @Override
    public Currency getByMeliId(String id) {

        Currency currency = currencyRepository.getByMeliId(id);
        
        if(currency == null){
            com.ventalandia.meli.api.notification.Currency currencyMeli = meliService.getEntityFromMELI("/currencies/"+id, com.ventalandia.meli.api.notification.Currency.class);
            currency = currencyTransformer.transform(currencyMeli);
        }
        
        return currency;
    }

}
