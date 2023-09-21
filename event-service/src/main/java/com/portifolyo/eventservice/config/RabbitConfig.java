package com.portifolyo.eventservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

public class RabbitConfig {

    @Bean(name = "userQueue")
    public Queue userQueue() {
        return new Queue("user-queue",false);
    }

    @Bean(name = "userFanout")
    public FanoutExchange userFanoutExchange() {
        return new FanoutExchange("user-exchange");
    }

    @Bean(name = "userBinding")
    public Binding userBinding(@Qualifier("userQueue") Queue q, @Qualifier("userFanout") FanoutExchange f) {
        return BindingBuilder.bind(q).to(f);
    }


    @Bean(name = "ticketQueue")
    public Queue ticketQueue() {
        return new Queue("ticket-queue",false,true,true);
    }

    @Bean(name = "ticketFanout")
    public FanoutExchange ticketFanoutExchange() {
        return new FanoutExchange("ticket-exchange",true,false);
    }

    @Bean(name = "ticketBinding")
    public Binding ticketBinding(@Qualifier("ticketQueue") Queue queue, @Qualifier("ticketFanout") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

}
