package com.portifolyo.userservice.services;

import com.portifolyo.userservice.config.EmailConfig;
import com.portifolyo.userservice.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.portifolyo.requests.notificationservice.NotificationRequest;
import org.portifolyo.requests.notificationservice.NotificationType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailService {
    private final TemplateEngine templateEngine;
    private final RabbitTemplate rabbitTemplate;
    private final EmailConfig emailConfig;

    @Async
    public void sendMail(User u){
        Context context = new Context();
        context.setVariable("user",u);
        String process = templateEngine.process("email",context);
        Map<String,String> info = new HashMap<>();
        info.put("to",u.getEmail());
        info.put("password", emailConfig.getPassword());
        info.put("email", emailConfig.getUserName());
        info.put("host", emailConfig.getHost());
        info.put("port",String.valueOf(emailConfig.getPort()));
        info.put("context",process);
        NotificationRequest notificationRequest = new NotificationRequest(NotificationType.EMAIL,info);
        rabbitTemplate.convertAndSend("notification","notification-router",notificationRequest);

    }

}
