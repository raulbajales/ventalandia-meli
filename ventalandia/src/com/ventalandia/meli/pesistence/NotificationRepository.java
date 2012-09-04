package com.ventalandia.meli.pesistence;

import java.util.List;

import javax.jdo.Query;

import com.ventalandia.framework.persistence.JdoRepository;
import com.ventalandia.meli.api.notification.Notification;

/**
 * 
 * @author german
 * @author matias
 * 
 */
public class NotificationRepository extends JdoRepository<Notification> {

	@SuppressWarnings("unchecked")
	public List<Notification> getUnreadQuestionsByUserId(int userId) {

		Query query = getPersistenceManager().newQuery(
				"select from com.ventalandia.meli.api.notification.Notification where topic == topicValue && user_id == userId && read == false " + "parameters String topicValue, Integer userId");

		return (List<Notification>) query.execute("questions", userId);
	}

}
