package com.banking.account.mapper;

import com.banking.account.dto.HistoryChangePassDTO;
import com.banking.account.entity.HistoryChangePass;

public class HistoryChangePassMapper {
    public static HistoryChangePassDTO toDTO(HistoryChangePass historyChangePass) {
        HistoryChangePassDTO dto = new HistoryChangePassDTO();
        dto.setId(historyChangePass.getId());
        dto.setOldPass(historyChangePass.getOldPass());
        dto.setNewPass(historyChangePass.getNewPass());
        dto.setAuthId(historyChangePass.getAuth().getId());
        dto.setDateChange(historyChangePass.getDateChange());
        return dto;
    }

    public static HistoryChangePass toEntity(HistoryChangePassDTO dto) {
        HistoryChangePass historyChangePass = new HistoryChangePass();
        historyChangePass.setId(dto.getId());
        historyChangePass.setOldPass(dto.getOldPass());
        historyChangePass.setNewPass(dto.getNewPass());
        // You will need to fetch the Auth entity by its id
        // historyChangePass.setAuth(auth);
        historyChangePass.setDateChange(dto.getDateChange());
        return historyChangePass;
    }
}
