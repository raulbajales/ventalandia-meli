package com.ventalandia.meli.service;

import com.ventalandia.meli.api.auth.AuthToken;

/**
 * 
 * @author matias
 *
 */
public interface MeliService {

	AuthToken getAuthToken(String code);

	boolean validate(AuthToken authToken);

}
