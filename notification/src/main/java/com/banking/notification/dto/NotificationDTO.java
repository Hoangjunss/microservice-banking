package com.banking.notification.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private Integer id;
    private String message;
    private Integer userId; // Id của người nhận thông báo
    private String url;
    private Boolean isRead;
    private LocalDateTime createAt;
}
