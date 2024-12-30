package com.banking.account.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Auth {
    private Integer id;
    private String email;
    private String password;
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
}
