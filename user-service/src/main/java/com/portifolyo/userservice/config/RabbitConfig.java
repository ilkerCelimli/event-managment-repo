package com.portifolyo.userservice.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean(name = "user-queue")
    public Queue queue() {
        return new Queue("user-queue",true);
    }

    @Bean(name = "user-exchange")
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("user-exchange");
    }

    @Bean(name = "user-binding")
    public Binding binding(@Qualifier("user-queue") Queue q,@Qualifier("user-exchange") FanoutExchange f) {
        return BindingBuilder.bind(q).to(f);
    }

    @Bean(name = "notification-queue")
    public Queue notificationQueue(){return new Queue("notification");}

    @Bean(name = "notification-exchange")
    public FanoutExchange directExchange(){return new FanoutExchange("notification-exchange");}

    @Bean(name = "notification-binding")
    public Binding notificationBinding(@Qualifier("notification-queue") Queue q,@Qualifier("notification-exchange") FanoutExchange directExchange){
        return BindingBuilder.bind(q).to(directExchange);
    }
}
