package com.banking.notification.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private Integer id;
    private String message;
    private Integer userId; // Id của người nhận thông báo
    private Boolean status;
    private String url;
    private Boolean isRead;
    private LocalDateTime createAt;
}
