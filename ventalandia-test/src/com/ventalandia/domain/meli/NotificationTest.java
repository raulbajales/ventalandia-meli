package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.ventalandia.meli.domain.Notification;

/**
 * 
 * @author msulik
 *
 */
public class NotificationTest extends DomainTest {

	@Inject
	private Gson gson;
	
	@Test
	public void test() {
		String json = this.createNotificationJson();
		
		Notification notification = gson.fromJson(json, Notification.class);
		assertNotNull(notification);
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

}
