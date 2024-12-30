package com.banking.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private Integer id;
    private BigDecimal balance;
    private Integer accountStatusId;
    private LocalDateTime dateCreate;

}
