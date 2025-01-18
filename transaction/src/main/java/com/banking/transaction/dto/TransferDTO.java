package com.banking.transaction.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TransferDTO {
    private Integer id;
    private Integer idAccountSend;
    private Integer idAccountReceive;
    private Integer idBalance;
    private Integer idStatusTransfer;
    private LocalDateTime createAt;
}
