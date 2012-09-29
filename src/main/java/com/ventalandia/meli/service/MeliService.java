package com.ventalandia.meli.service;

import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author matias
 * 
 */
public interface MeliService {

    String apiUrl = "https://api.mercadolibre.com";

    AuthToken getAuthToken(String code);

    AuthToken refreshAuthToken(String refreshToken);

    <T> T getEntityFromMELI(String resource, Class<T> clazz);

}
