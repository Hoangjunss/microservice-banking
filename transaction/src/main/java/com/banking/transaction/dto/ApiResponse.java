package com.banking.transaction.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
    private List<String> errors;
}
