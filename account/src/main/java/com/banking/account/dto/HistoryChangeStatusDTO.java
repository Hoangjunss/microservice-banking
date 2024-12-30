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
public class HistoryChangeStatusDTO {
    private Integer id;
    private Integer oldAccountStatusId;
    private Integer newAccountStatusId;
    private Integer accountId;
    private LocalDateTime dateChangeAccountStatus;


}
