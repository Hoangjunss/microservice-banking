package com.banking.notification.service;

import com.banking.notification.dto.MailDTO;
import com.banking.notification.dto.MessageDTO;
import com.banking.notification.entity.Mail;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender javaMailSender; // thư viện hỗ trợ gửi mail

    @Override
    public Mail getMail(String recipientEmail, String subject, String text) {
        return Mail.builder()
                .receiverEmail(recipientEmail) // email người nhận
                .subjectEmail(subject) // tiêu đề email
                .contentEmail(text) // nội dung email
                .contentType("text/plain") // loại nội dung email (văn bản)
                .attachment(null) // không có file đính kèm
                .senderEmail("cvreviewbaconbao@gmail.com") // email người gửi
                .build();
    }

    /**
     * Gửi mail
     *
     * Phương thức này thực hiện các bước sau:
     * 1. Tạo một email mới (email rỗng) bằng cách sử dụng `javaMailSender.createMimeMessage()` để tạo một email rỗng (MimeMessage)..
     * 2. Chuẩn bị nội dung email bằng cách sử dụng `MimeMessageHelper`:
     * - Tạo một đối tượng `MimeMessageHelper` với tham số là email vừa tạo.
     * 3. Thiết lập thông tin email:
     *  - Thiết lập người gửi bằng cách sử dụng `mimeMessageHelper.setFrom(new InternetAddress(mail.getSenderEmail()))`. InternetAddress là một lớp trong Java Mail API, được sử dụng để đại diện cho địa chỉ email.
     *  - Thiết lập người nhận bằng cách sử dụng `mimeMessageHelper.setTo(mail.getReceiverEmail())`.
     *  - Thiết lập tiêu đề bằng cách sử dụng `mimeMessageHelper.setSubject(mail.getSubjectEmail())`.
     *  - Thiết lập nội dung bằng cách sử dụng `mimeMessageHelper.setText(mail.getContentEmail())`.
     *  - Gửi email bằng cách sử dụng `javaMailSender.send(mimeMessageHelper.getMimeMessage())`.
     *  - Nếu có lỗi, in ra stack trace.
     * @param mail Đối tượng `Mail` chứa thông tin về email cần gửi.
     *
     */
    @Override
    public void sendMail(Mail mail) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage(); // tạo một thư mới
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true); // tạo một trợ giúp cho thư

            // Thiết lập thông tin email
            mimeMessageHelper.setFrom(new InternetAddress(mail.getSenderEmail())); // người gửi
            mimeMessageHelper.setTo(mail.getReceiverEmail()); // người nhận
            mimeMessageHelper.setSubject(mail.getSubjectEmail()); // tiêu đề
            mimeMessageHelper.setText(mail.getContentEmail()); // nội dung
            // gửi mail
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(MessageDTO messageDTO) {
        if(messageDTO == null) {
            throw new IllegalArgumentException("MessageDTO cannot be null");
        }
        MailDTO mailDTO = MailDTO.builder()
                .senderEmail(messageDTO.getMessage())
                .subjectEmail(messageDTO.getMessage())
                .contentEmail(messageDTO.getMessage())
                .build();
        Mail mail = getMail(mailDTO.getSenderEmail(), mailDTO.getSubjectEmail(), mailDTO.getContentEmail());
        sendMail(mail);
    }
}
