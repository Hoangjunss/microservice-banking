package com.banking.transaction.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Transaction {
    @Id
    private Integer id;
    private Integer balanceId;
    private LocalDateTime createAt;
    private Integer transferId;

    @ManyToOne
    @JoinColumn(name = "status_transaction_id")
    private StatusTransaction statusTransaction;

    @ManyToOne
    @JoinColumn(name = "status_action_id")
    private StatusAction statusAction;
}
