package com.banking.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class HistoryChangeStatus {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "old_account_status_id")
    private AccountStatus oldAccountStatus;
    @ManyToOne
    @JoinColumn(name = "new_account_status_id")
    private AccountStatus newAccountStatus;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    private LocalDateTime DateChangeAccountStatus;
}
