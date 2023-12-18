package com.example.cinemachainmanagement.service.impl;

import com.example.cinemachainmanagement.controller.BookTicketController;
import com.example.cinemachainmanagement.entities.ShoppingCartItem;
import com.example.cinemachainmanagement.entities.Ticket;
import com.example.cinemachainmanagement.entities.Transaction;
import com.example.cinemachainmanagement.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    private final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender emailSender;
    @Override
    public void sendEmail(String to, String subject, String text) {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // Set true for HTML content

            emailSender.send(message);
        }catch(Exception e){
            logger.info(e.getMessage());
        }

    }
    @Override
    public void sendInvoiceEmail(String to, String subject, List<Ticket> tickets, Transaction transaction, List<ShoppingCartItem> shoppingCartItems) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);

            // Tạo nội dung email từ thông tin hóa đơn và giao dịch
            StringBuilder content = new StringBuilder();
            content.append("<h2>Invoice Information</h2>");
            content.append("<p><strong>Customer Name:</strong> ").append(transaction.getCustomer().getFirstName()).append("</p>");
            content.append("<p><strong>Transaction ID:</strong> ").append(transaction.getTransaction_id()).append("</p>");
            //content.append("<p><strong>Transaction Date:</strong> ").append(org.springframework.format.datetime.standard.DateTimeFormatter.ISO_DATE.format(transaction.getCreated())).append("</p>");

//            // Thêm thông tin về các vé và sản phẩm vào email
//            content.append("<h3>Tickets Purchased:</h3>");
//            for (Ticket ticket : tickets) {
//                // Thêm thông tin từ các vé vào nội dung email
//                content.append("<p>").append(ticket.getTicketId()).append("</p>");
//            }
//
//            content.append("<h3>Shopping Cart Items:</h3>");
//            for (ShoppingCartItem item : shoppingCartItems) {
//                // Thêm thông tin từ các sản phẩm trong giỏ hàng vào nội dung email
//                content.append("<p>").append(item.getShoppingCartItemId()).append("</p>");
//            }

            // Set nội dung email và gửi
            helper.setText(content.toString(), true); // Set true for HTML content
            emailSender.send(message);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
