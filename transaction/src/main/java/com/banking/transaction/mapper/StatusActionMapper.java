package com.banking.transaction.mapper;

import com.banking.transaction.dto.StatusActionDTO;
import com.banking.transaction.entity.StatusAction;

public class StatusActionMapper {
    public static StatusActionDTO toDTO(StatusAction statusAction) {
        StatusActionDTO dto = new StatusActionDTO();
        dto.setId(statusAction.getId());
        dto.setName(statusAction.getName());
        return dto;
    }

    public static StatusAction toEntity(StatusActionDTO dto) {
        StatusAction statusAction = new StatusAction();
        statusAction.setId(dto.getId());
        statusAction.setName(dto.getName());
        return statusAction;
    }
}
