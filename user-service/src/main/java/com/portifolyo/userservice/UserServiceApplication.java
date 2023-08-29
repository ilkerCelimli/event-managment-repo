package com.portifolyo.userservice;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableRabbit
@EnableMongoAuditing
@EnableAsync
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
