package com.springRabbitMQ;

import java.io.IOException;
import java.security.PublicKey;
import java.util.concurrent.TimeoutException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.AMQP.Basic.Publish;

@SpringBootApplication
public class SpringRabbitMqApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(SpringRabbitMqApplication.class, args);
		publish();
		//consumer();
	}
	
	private static void publish() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		String message = "4º Message";
		
		channel.basicPublish("", "queue-1", null, message.getBytes());
		
		channel.close();
		connection.close();
	}
	
	private static void consumer() throws IOException, TimeoutException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.newConnection();
		Channel channel = connection.createChannel();
		
		DeliverCallback callback = (consumerTag, delivery ) -> {
			String message = new String(delivery.getBody());
			System.out.println("Message received : " + message);
		};
		
		channel.basicConsume("queue-1", true, callback, consumerTag -> {});
		
	}
}
