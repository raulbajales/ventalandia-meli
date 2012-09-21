package com.ventalandia.service;

import com.google.inject.Inject;
import com.ventalandia.domain.Country;
import com.ventalandia.domain.transformer.CountryTransformer;
import com.ventalandia.meli.pesistence.CountryRepository;
import com.ventalandia.meli.service.MeliService;

public class CountryServiceImpl implements CountryService{

    private CountryRepository countryRepository;
    private CountryTransformer countryTransformer;
    private MeliService meliService;
    
    
    @Inject
    public CountryServiceImpl(CountryRepository countryRepository, CountryTransformer countryTransformer, MeliService meliService) {
    
        this.countryRepository = countryRepository;
        this.countryTransformer = countryTransformer;
        this.meliService = meliService;
    }



    public Country getByMeliId(String country_id) {

        Country country = countryRepository.getByMeliId(country_id);
        if(country == null){
            com.ventalandia.meli.api.notification.Country countryMeli = meliService.getEntityFromMELI("/countries/"+country_id, com.ventalandia.meli.api.notification.Country.class);
            country = countryTransformer.transform(countryMeli);
            countryRepository.add(country);
        }
        
        return country;
    }

}
