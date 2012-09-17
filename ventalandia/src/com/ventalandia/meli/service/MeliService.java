package com.ventalandia.meli.service;

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

}
