package com.portifolyo.notificationservice;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableRabbit
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean(name = "notification-queue")
	public Queue notificationQueue(){return new Queue("notification",true);}

	@Bean(name = "notification-exchange")
	public FanoutExchange directExchange(){return new FanoutExchange("notification-exchange");}

	@Bean(name = "notification-binding")
	public Binding notificationBinding(@Qualifier("notification-queue") Queue q, @Qualifier("notification-exchange") FanoutExchange directExchange){
		return BindingBuilder.bind(q).to(directExchange);
	}
}
