package com.banking.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Transfer {
    @Id
    private Integer id;
    private Integer idAccountSend;
    private Integer idAccountReceive;
    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "status_transfer_id")
    private StatusTransfer statusTransfer;
    private LocalDateTime createAt;
}
