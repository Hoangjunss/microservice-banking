package com.banking.notification.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailDTO {
    private String senderEmail; // Địa chỉ email của người gửi
    private String subjectEmail; // Tiêu đề email
    private String contentEmail; // Nội dung email
}
