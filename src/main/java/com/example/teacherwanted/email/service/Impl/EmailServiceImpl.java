package com.example.teacherwanted.email.service.Impl;

import com.example.teacherwanted.email.model.EmailDetails;
import com.example.teacherwanted.email.service.EmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public String sendSimpleMail(EmailDetails details) {
        return null;
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        return null;
    }
}
