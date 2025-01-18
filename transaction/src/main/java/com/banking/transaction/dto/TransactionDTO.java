package com.banking.transaction.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransactionDTO {
    private Integer id;
    private Integer balanceId;
    private LocalDateTime createAt;

    private Integer idStatusTransaction;
    private Integer idStatusAction;
}

