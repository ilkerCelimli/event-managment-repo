package com.portifolyo.notificationservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.notificationservice.NotificationRequest;
import org.portifolyo.requests.notificationservice.NotificationType;
import org.portifolyo.utils.DeserializeHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Properties;
@Slf4j
@Service
public class SendMail {


    @RabbitListener(queues = "notification")
    public void notification(NotificationRequest data){
        if (data.getType() == NotificationType.EMAIL) {
            try {
                JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
                javaMailSender.setHost(data.getInfo().get("host"));
                javaMailSender.setPort(Integer.parseInt(data.getInfo().get("port")));
                javaMailSender.setUsername(data.getInfo().get("email"));
                javaMailSender.setPassword(data.getInfo().get("password"));
                Properties props = javaMailSender.getJavaMailProperties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.debug", "true");
                javaMailSender.setJavaMailProperties(props);


                MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
                mimeMessageHelper.setSubject(data.getInfo().get("subject"));
                mimeMessageHelper.setText(data.getInfo().get("context"), true);
                mimeMessageHelper.setTo(data.getInfo().get("to"));
                javaMailSender.send(mimeMessageHelper.getMimeMessage());
                log.info("sended mail");
            }
            catch (MessagingException ex){
                System.out.println("Hata");
                log.error(ex.toString());
            }
        }
    }
}

