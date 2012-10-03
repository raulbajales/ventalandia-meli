package com.ventalandia.meli.pesistence;

import com.google.inject.Inject;
import com.ventalandia.domain.User;
import com.ventalandia.framework.persistence.MeliJdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;

public class UserRepositoryImpl extends MeliJdoRepository<User> implements UserRepository{

	@Inject
	public UserRepositoryImpl(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}
    
}
