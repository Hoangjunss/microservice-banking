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
public class Balance {
    @Id
    private Integer id;
    private Integer accountId;
    private BigDecimal balance;
    private boolean status;
    private LocalDateTime createAt;
}
