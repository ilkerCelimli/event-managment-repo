package com.portifolyo.userservice.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Getter
@Component
public class EmailConfig {

    private final String userName;
    private final String password;
    private final String host;
    private final Integer port;

    public EmailConfig(@Value("${email.host}") String host,
                       @Value("${email.password}") String password, @Value("${email.username}")String username,
                       @Value("${email.port}") Integer port) {
        this.host = host;
        this.port = port;
        this.userName = username;
        this.password = password;

    }




}
