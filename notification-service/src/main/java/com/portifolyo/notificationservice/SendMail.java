package com.portifolyo.notificationservice;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.notificationservice.NotificationRequest;
import org.portifolyo.requests.notificationservice.NotificationType;
import org.portifolyo.utils.DeserializeHelper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.Properties;
@Slf4j
public class SendMail {


    @RabbitListener(queues = "notification-exchange")
    public void notification(byte[] data) throws MessagingException {
        NotificationRequest notificationRequest = DeserializeHelper.desarialize(data);
        if(notificationRequest.getType() == NotificationType.EMAIL){
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setHost(notificationRequest.getInfo().get("host"));
            javaMailSender.setPort(Integer.valueOf(notificationRequest.getInfo().get("port")));
            javaMailSender.setUsername(notificationRequest.getInfo().get("email"));
            javaMailSender.setPassword(notificationRequest.getInfo().get("password"));
            Properties props = javaMailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            javaMailSender.setJavaMailProperties(props);


            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject(notificationRequest.getInfo().get("subject"));
            mimeMessageHelper.setText(notificationRequest.getInfo().get("context"),true);
            mimeMessageHelper.setTo(notificationRequest.getInfo().get("to"));
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("sended mail");
        }
    }

}
