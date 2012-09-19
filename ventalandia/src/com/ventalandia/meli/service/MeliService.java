package com.ventalandia.meli.service;

import com.ventalandia.domain.Token;
import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author matias
 *
 */
public interface MeliService {

	AuthToken getAuthToken(String code);
	
	AuthToken refreshAuthToken(String refreshToken);

	boolean validate(AuthToken authToken);
	
	boolean validate(Token token);
	
	<T> T getEntityFromMELI(String resource, Class<T> clazz) ;

}
