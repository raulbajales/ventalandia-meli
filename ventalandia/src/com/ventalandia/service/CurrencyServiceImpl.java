package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.Currency;
import com.ventalandia.domain.transformer.CurrencyTransformer;
import com.ventalandia.meli.pesistence.CurrencyRepository;
import com.ventalandia.meli.service.MeliService;

public class CurrencyServiceImpl implements CurrencyService{

    private CurrencyRepository currencyRepository;
    private CurrencyTransformer currencyTransformer;
    private MeliService meliService;
    
    
    @Inject
    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyTransformer currencyTransformer, MeliService meliService) {
        this.currencyRepository = currencyRepository;
        this.currencyTransformer = currencyTransformer;
        this.meliService = meliService;
    }



    @Override
    public Currency getByMeliId(String id) {

        Currency currency = currencyRepository.getByMeliId(id);
        
        if(currency == null){
            com.ventalandia.meli.api.notification.Currency currencyMeli = meliService.getEntityFromMELI("/currencies/"+id, com.ventalandia.meli.api.notification.Currency.class);
            currency = currencyTransformer.transform(currencyMeli);
            currencyRepository.add(currency);
        }
        
        return currency;
    }

}
