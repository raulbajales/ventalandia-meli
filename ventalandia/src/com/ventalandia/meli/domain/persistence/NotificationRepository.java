package com.ventalandia.meli.domain.persistence;

import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.meli.domain.Notification;

/**
 * 
 * @author german
 * @author matias
 * 
 */
public class NotificationRepository extends JdoRepository<Notification> {

	@Override
	protected Class<Notification> getPersistedType() {
		return Notification.class;
	}

}
