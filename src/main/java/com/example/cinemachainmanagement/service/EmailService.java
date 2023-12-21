package com.example.cinemachainmanagement.service;

import com.example.cinemachainmanagement.entities.ShoppingCartItem;
import com.example.cinemachainmanagement.entities.Ticket;
import com.example.cinemachainmanagement.entities.Transaction;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
    public void sendInvoiceEmail(String to, String subject, List<Ticket>tickets, Transaction transaction, List<ShoppingCartItem> shoppingCartItems);
}
