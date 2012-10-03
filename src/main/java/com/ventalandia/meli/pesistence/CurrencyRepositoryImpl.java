package com.ventalandia.meli.pesistence;

import com.google.inject.Inject;
import com.ventalandia.domain.Currency;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class CurrencyRepositoryImpl extends MeliJdoRepository<Currency> implements CurrencyRepository{

	@Inject
	public CurrencyRepositoryImpl(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

}
