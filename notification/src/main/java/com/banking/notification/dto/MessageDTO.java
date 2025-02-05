package com.banking.notification.dto;

import lombok.*;

@Data
@Builder
public class MessageDTO {
    private String message;
    private Integer id;
}
