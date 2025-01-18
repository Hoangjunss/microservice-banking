package com.banking.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "balance_id")
    private Balance balance;

    @ManyToOne
    @JoinColumn(name = "status_transfer_id")
    private StatusTransfer statusTransfer;
    private LocalDateTime createAt;
}
