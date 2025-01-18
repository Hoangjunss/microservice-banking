package com.banking.transaction.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StatusTransferDTO {
    private Integer id;
    private String name;
}
