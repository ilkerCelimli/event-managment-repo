package com.portifolyo.userservice.services;

import com.portifolyo.userservice.entity.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
@RequiredArgsConstructor
@Log4j2
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Async
    public Future<String> sendMail(User u){
        Context context = new Context();
        context.setVariable("user",u);
        String process = templateEngine.process("email",context);
        try{

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("Hoşgeldiniz "+ u.getName());
            mimeMessageHelper.setText(process,true);
            mimeMessageHelper.setTo(u.getEmail());
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            return CompletableFuture.completedFuture("email");
        }
        catch (MessagingException e){
            return null;
        }

    }

}
