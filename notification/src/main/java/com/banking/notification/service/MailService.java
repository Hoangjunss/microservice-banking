package com.banking.notification.service;

import com.banking.notification.dto.MessageDTO;
import com.banking.notification.entity.Mail;
import org.springframework.stereotype.Service;

@Service
public interface MailService {
    Mail getMail(String recipientEmail, String subject, String text); // Tạo mail
    void sendMail(Mail mail); // Gửi mail
    void send(MessageDTO messageDTO); // Gửi email từ thông báo
}
