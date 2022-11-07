package com.greatlearning.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void send(String subject, String message) {
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setFrom("doconnect657@gmail.com");
        simpleMailMessage.setTo("ronyshaikh313@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);

        this.javaMailSender.send(simpleMailMessage);

    }
}
