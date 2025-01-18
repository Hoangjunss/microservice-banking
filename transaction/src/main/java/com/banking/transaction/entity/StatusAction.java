package com.banking.transaction.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class StatusAction {
    @Id
    private Integer id;
    private String name;
}
