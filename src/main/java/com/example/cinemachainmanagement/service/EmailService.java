package com.example.cinemachainmanagement.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
}
