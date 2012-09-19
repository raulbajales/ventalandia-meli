package com.ventalandia.meli.service;

import com.ventalandia.domain.Currency;

/**
 * 
 * @author gzanussi
 *
 */
public interface CurrencyService {

    Currency getByMeliId(String id);

}
