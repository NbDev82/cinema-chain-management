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

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

            content.append("<img src='https://www.galaxycine.vn/_next/image/?url=%2F_next%2Fstatic%2Fmedia%2Fgalaxy-logo-mobile.074abeac.png&w=128&q=75'>");

            content.append("<p><strong>Hello</strong> ")
                    .append(transaction.getCustomer().getFirstName())
                    .append(" ") // Thêm dấu cách giữa FirstName và LastName
                    .append(transaction.getCustomer().getLastName())
                    .append("<strong> We confirm that you have booked tickets at CineStarHoTa on</strong>")
                    .append(" ")
                    .append(transaction.getCreated())
                    .append("</p>");

            content.append("<p><strong>Customer Name:</strong>").append(transaction.getCustomer().getFirstName()).append("").append(transaction.getCustomer().getLastName()).append("</p>");
            content.append("<p><strong>Transaction ID:</strong>").append(transaction.getTransaction_id()).append("</p>");
            content.append("<p>Tickets Purchased: ");
            int ticketCount = tickets.size();
            int counterTicket = 0;

            for (Ticket ticket : tickets) {
                counterTicket++;
                content.append("<span>").append(ticket.getSeat().getSeatNumber()).append("</span>");
                if (counterTicket < ticketCount) {
                    content.append(", ");
                }
            }
            content.append("</p>");

            content.append("<p>Food and drink list: ");
            int shoppingCartCount = shoppingCartItems.size();
            int counterShoppingCartItem = 0;

            for (ShoppingCartItem item : shoppingCartItems) {
                counterShoppingCartItem++;
                content.append("<span>")
                        .append(item.getProduct().getProductName())
                        .append(" x") // Thêm số lượng
                        .append(item.getShoppingCartItemQuantity()) // Lấy số lượng sản phẩm đã đặt
                        .append("</span>");

                if (counterShoppingCartItem < shoppingCartCount) {
                    content.append(", ");
                }
            }
            content.append("</p>");


            content.append("<p>Thank you for your purchase! If you have any questions or need further assistance, please feel free to contact us.</p>");
            content.append("<p>Best regards,</p>");
            content.append("<p>Hotline:1900 6017</p>");

            // Set nội dung email và gửi
            helper.setText(content.toString(), true); // Set true for HTML content
            emailSender.send(message);
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }

}
