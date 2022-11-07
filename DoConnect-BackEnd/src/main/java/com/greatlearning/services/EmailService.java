package com.greatlearning.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void send(String subject,String message);
}
