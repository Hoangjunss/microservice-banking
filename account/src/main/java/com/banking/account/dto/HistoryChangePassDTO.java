package com.banking.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryChangePassDTO {
    private Integer id;
    private String oldPass;
    private String newPass;
    private Integer authId;
    private LocalDateTime dateChange;

    // Getters and setters
}
