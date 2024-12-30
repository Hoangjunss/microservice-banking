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
public class HistoryChangePass {
    @Id
    private Integer id;
    private String oldPass;
    private String newPass;
    @ManyToOne
    @JoinColumn(name = "auth_id")
    private Auth auth;
    private LocalDateTime dateChange;
}
