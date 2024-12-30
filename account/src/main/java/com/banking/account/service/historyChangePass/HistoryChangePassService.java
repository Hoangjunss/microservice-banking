package com.banking.account.service.historyChangePass;

import com.banking.account.dto.HistoryChangePassDTO;

import java.util.List;

public interface HistoryChangePassService {
    List<HistoryChangePassDTO> getAllHistoryChangePasses();
    HistoryChangePassDTO getHistoryChangePassById(Integer id);
    HistoryChangePassDTO createHistoryChangePass(HistoryChangePassDTO historyChangePassDTO);
    HistoryChangePassDTO updateHistoryChangePass(Integer id, HistoryChangePassDTO historyChangePassDTO);
    void deleteHistoryChangePass(Integer id);
}
