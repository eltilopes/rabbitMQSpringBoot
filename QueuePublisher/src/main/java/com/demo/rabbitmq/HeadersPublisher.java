package com.demo.rabbitmq;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class HeadersPublisher {
	
	public static void main(String[] args) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		
		String message = "This is Headers-Exchange";
		
		Map<String, Object> headersMap = new HashMap<>();
		headersMap.put("item1", "mobile");
		headersMap.put("item2", "television");
		
		BasicProperties br = new BasicProperties();
		br = br.builder().headers(headersMap).build();
		
		channel.basicPublish("Headers-Exchange", "", br, message.getBytes());

		
		channel.close();
		connection.close();

	}
}