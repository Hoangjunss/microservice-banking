package com.banking.transaction.mapper;

import com.banking.transaction.dto.StatusTransactionDTO;
import com.banking.transaction.entity.StatusTransaction;

public class StatusTransactionMapper {
    public static StatusTransactionDTO toDTO(StatusTransaction statusTransaction) {
        StatusTransactionDTO dto = new StatusTransactionDTO();
        dto.setId(statusTransaction.getId());
        dto.setName(statusTransaction.getName());
        return dto;
    }

    public static StatusTransaction toEntity(StatusTransactionDTO dto) {
        StatusTransaction statusTransaction = new StatusTransaction();
        statusTransaction.setId(dto.getId());
        statusTransaction.setName(dto.getName());
        return statusTransaction;
    }
}
