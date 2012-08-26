package com.ventalandia.domain.meli;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author msulik
 *
 */
public class NotificationTest {

	private Gson gson = this.createGson();
	
	@Test
	public void test() {
		String json = this.createNotificationJson();
		
		Notification notification = gson.fromJson(json, Notification.class);
		
		assertNotNull(notification);
	}

	private Gson createGson() {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();
		return gson;
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
