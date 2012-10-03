package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.google.inject.Inject;
import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.framework.persistence.PersistenceManagerProvider;
import com.ventalandia.meli.api.notification.Notification;

/**
 * 
 * @author german
 * @author matias
 * 
 */
public class NotificationRepository extends JdoRepository<Notification> {

	@Inject
	public NotificationRepository(PersistenceManagerProvider persistenceManagerProvider) {
		super(persistenceManagerProvider);
	}

	@SuppressWarnings("unchecked")
	public List<Notification> getUnreadQuestionsByUserId(long userId) {

		Query query = this.createQuery();
		query.setFilter("topic == topicValue && user_id == userId && read == false");
		query.declareParameters("String topicValue, Long userId");

		return (List<Notification>) query.execute("questions", userId);
	}

	@SuppressWarnings("unchecked")
	public List<Notification> getUnreadQuestionsBySellerId(long sellerMeliId) {

		Query query = this.createQuery();
		query.setFilter("topic == topicValue && sellerId == userId");
		query.declareParameters("String topicValue, Long userId");

		return (List<Notification>) query.execute("questions", sellerMeliId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Notification> getUnreadNotifications(final String topic) {

		Query query = this.createQuery();
		query.setFilter("topic == topicValue && read == false");
		query.declareParameters("String topicValue");
		
		return (List<Notification>) query.execute(topic);
	}

}
