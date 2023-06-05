package com.example.teacherwanted.email.service;

import com.example.teacherwanted.email.model.EmailDetails;

public interface EmailService {
    // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(EmailDetails details);
}
