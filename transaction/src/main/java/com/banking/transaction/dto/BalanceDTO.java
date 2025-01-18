package com.banking.transaction.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BalanceDTO {
    private Integer id;
    private BigDecimal balance;
    private Integer accountId;
    private LocalDateTime createAt;
    private boolean status;
}
