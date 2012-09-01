package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Inject;
import com.ventalandia.meli.domain.notification.Notification;
import com.ventalandia.meli.domain.notification.Question;

/**
 * 
 * @author msulik
 *
 */
public class NotificationTest {

	@Inject
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();
	
	@Test
	public void test() {
		String json = this.createNotificationJson();
		
		Notification notification = gson.fromJson(json, Notification.class);
		assertNotNull(notification);
	}

	@Test
	public void testNotificationContent() {
		
		String json = this.createNotificationContent();
		Question notificationContent = gson.fromJson(json, Question.class);
		assertNotNull(notificationContent);
	}

	
	private String createNotificationJson() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"user_id\": 1234,");
		builder.append("\"resource\": \"/questions/139876\",");
		builder.append("\"topic\": \"questions\",");
		builder.append("\"received\": \"2011-10-19T16:38:34.425Z\",");
		builder.append("\"sent\" : \"2011-10-19T16:40:34.425Z\"");
		builder.append("}");
		return builder.toString();
	}

	private String createNotificationContent(){
		return "{\"id\":2455498075,\"answer\":{\"date_created\":\"2012-08-31T14:59:46.000-04:00\",\"status\":\"ACTIVE\",\"text\"" +
				":\"GERMANTANO  Hola , si estamos de 10 a 13 el sabado. saludos.sd\"},\"date_created\":\"2012-08-31T14:54:15" +
				".000-04:00\",\"item_id\":\"MLA430494065\",\"seller_id\":86898669,\"status\":\"ANSWERED\",\"text\":\"Hola, trabajan " +
				"los sabados?\",\"from\":{\"id\":79450083}}";
	}
	
}
