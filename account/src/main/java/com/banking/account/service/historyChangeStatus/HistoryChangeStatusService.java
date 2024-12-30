package com.banking.account.service.historyChangeStatus;

import com.banking.account.dto.HistoryChangeStatusDTO;

import java.util.List;

public interface HistoryChangeStatusService {
    List<HistoryChangeStatusDTO> getAllHistoryChangeStatuses();
    HistoryChangeStatusDTO getHistoryChangeStatusById(Integer id);
    HistoryChangeStatusDTO createHistoryChangeStatus(HistoryChangeStatusDTO historyChangeStatusDTO);
    HistoryChangeStatusDTO updateHistoryChangeStatus(Integer id, HistoryChangeStatusDTO historyChangeStatusDTO);
    void deleteHistoryChangeStatus(Integer id);
}
