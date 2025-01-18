package com.banking.transaction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StatusTransactionDTO {
    private Integer id;
    private String name;
}
