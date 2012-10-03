package com.ventalandia.meli.pesistence;

import com.google.inject.Inject;
import com.ventalandia.domain.Item;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class ItemRepositoryImpl extends MeliJdoRepository<Item> implements ItemRepository{

	@Inject
	public ItemRepositoryImpl(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

}
