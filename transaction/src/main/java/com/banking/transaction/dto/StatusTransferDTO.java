package com.banking.transaction.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class StatusTransferDTO {
    private Integer id;
    private String name;
}
