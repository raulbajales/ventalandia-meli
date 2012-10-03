package com.ventalandia.meli.pesistence;

import com.google.inject.Inject;
import com.ventalandia.domain.Country;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class CountryRepositoryImpl extends MeliJdoRepository<Country> implements CountryRepository{

	@Inject
	public CountryRepositoryImpl(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}
    
}