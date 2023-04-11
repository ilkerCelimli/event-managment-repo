package com.portifolyo.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
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

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);

        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);

        Properties props = javaMailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }


}
