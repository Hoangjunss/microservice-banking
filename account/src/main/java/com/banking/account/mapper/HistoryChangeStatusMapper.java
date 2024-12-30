package com.banking.account.mapper;

import com.banking.account.dto.HistoryChangeStatusDTO;
import com.banking.account.entity.HistoryChangeStatus;

public class HistoryChangeStatusMapper {
    public static HistoryChangeStatusDTO toDTO(HistoryChangeStatus historyChangeStatus) {
        HistoryChangeStatusDTO dto = new HistoryChangeStatusDTO();
        dto.setId(historyChangeStatus.getId());
        dto.setOldAccountStatusId(historyChangeStatus.getOldAccountStatus().getId());
        dto.setNewAccountStatusId(historyChangeStatus.getNewAccountStatus().getId());
        dto.setAccountId(historyChangeStatus.getAccount().getId());
        dto.setDateChangeAccountStatus(historyChangeStatus.getDateChangeAccountStatus());
        return dto;
    }

    public static HistoryChangeStatus toEntity(HistoryChangeStatusDTO dto) {
        HistoryChangeStatus historyChangeStatus = new HistoryChangeStatus();
        historyChangeStatus.setId(dto.getId());
        // You will need to fetch the AccountStatus entities by their ids
        // historyChangeStatus.setOldAccountStatus(oldAccountStatus);
        // historyChangeStatus.setNewAccountStatus(newAccountStatus);
        // You will need to fetch the Account entity by its id
        // historyChangeStatus.setAccount(account);
        historyChangeStatus.setDateChangeAccountStatus(dto.getDateChangeAccountStatus());
        return historyChangeStatus;
    }
}
