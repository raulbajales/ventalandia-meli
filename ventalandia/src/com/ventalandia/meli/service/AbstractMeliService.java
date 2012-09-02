package com.ventalandia.meli.service;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.meli.domain.HttpConnector;
import com.ventalandia.view.MeliCallbackUrlApi;
import com.ventalandia.view.MeliClientIdApi;
import com.ventalandia.view.MeliClientSecretApi;

/**
 * 
 * @author matias
 * 
 */
public class AbstractMeliService {

	@Inject
	@MeliClientSecretApi
	protected String clientSecret;

	@Inject
	@MeliClientIdApi
	protected Integer clientId;

	@Inject
	@MeliCallbackUrlApi
	protected String callback;

	@Inject
	protected Gson gson;

	protected HttpConnector http = new HttpConnector();

}
