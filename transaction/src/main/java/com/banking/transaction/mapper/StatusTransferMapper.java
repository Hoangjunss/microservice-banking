package com.banking.transaction.mapper;

import com.banking.transaction.dto.StatusTransferDTO;
import com.banking.transaction.entity.StatusTransfer;

public class StatusTransferMapper {
    public static StatusTransferDTO toDTO(StatusTransfer statusTransfer) {
        StatusTransferDTO dto = new StatusTransferDTO();
        dto.setId(statusTransfer.getId());
        dto.setName(statusTransfer.getName());
        return dto;
    }

    public static StatusTransfer toEntity(StatusTransferDTO dto) {
        StatusTransfer statusTransfer = new StatusTransfer();
        statusTransfer.setId(dto.getId());
        statusTransfer.setName(dto.getName());
        return statusTransfer;
    }
}
