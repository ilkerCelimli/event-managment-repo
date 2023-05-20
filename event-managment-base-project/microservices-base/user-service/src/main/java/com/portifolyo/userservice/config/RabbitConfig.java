package com.portifolyo.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue() {
        return new Queue("user-queue");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("user-exchange");
    }

    @Bean
    public Binding binding(Queue q, FanoutExchange f) {
        return BindingBuilder.bind(q).to(f);
    }
}
