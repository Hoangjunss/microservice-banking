package com.banking.notification.entity;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {
    private String senderEmail; // Địa chỉ email của người gửi
    private String receiverEmail; // Địa chỉ email của người nhận
    private String ccEmails; // Địa chỉ email những người nhận được CC (Carbon Copy – nhận bản sao)
    private String bccEmails; // Địa chỉ email những người nhận được BCC (Blind Carbon Copy – nhận bản sao ẩn danh)

    private String subjectEmail; // Tiêu đề email
    private String contentEmail; // Nội dung email
    private String contentType; // Loại nội dung email (văn bản, html, …)
    private List<Object> attachment; // Danh sách file đính kèm

    // Trả về thời gian gửi email
    private Date getSendDate() {
        return new Date();
    }
}
