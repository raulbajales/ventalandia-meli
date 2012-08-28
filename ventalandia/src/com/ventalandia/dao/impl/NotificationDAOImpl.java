package com.ventalandia.dao.impl;

import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Inject;
import com.ventalandia.dao.NotificationDAO;
import com.ventalandia.meli.domain.Notification;

/**
 * 
 * @author german
 *
 */
public class NotificationDAOImpl extends AbstractDAO<Notification> implements NotificationDAO{

	@Inject
	public NotificationDAOImpl(PersistenceManagerFactory pmf) {
		super(pmf);
	}

}
