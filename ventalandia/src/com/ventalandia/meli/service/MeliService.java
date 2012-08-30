package com.ventalandia.meli.service;

import com.ventalandia.meli.domain.AuthToken;

/**
 * 
 * @author matias
 *
 */
public interface MeliService {

	AuthToken getAuthToken(String code);

	boolean validate(AuthToken authToken);

}
