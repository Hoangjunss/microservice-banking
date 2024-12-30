package com.banking.account.service.historyChangeStatus;

import com.banking.account.dto.HistoryChangeStatusDTO;
import com.banking.account.mapper.HistoryChangeStatusMapper;
import com.banking.account.entity.HistoryChangeStatus;
import com.banking.account.repository.HistoryChangeAccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryChangeStatusServiceImpl implements HistoryChangeStatusService {

    @Autowired
    private HistoryChangeAccountStatusRepository historyChangeStatusRepository;

    @Override
    public List<HistoryChangeStatusDTO> getAllHistoryChangeStatuses() {
        return historyChangeStatusRepository.findAll().stream()
                .map(HistoryChangeStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoryChangeStatusDTO getHistoryChangeStatusById(Integer id) {
        return historyChangeStatusRepository.findById(id)
                .map(HistoryChangeStatusMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public HistoryChangeStatusDTO createHistoryChangeStatus(HistoryChangeStatusDTO historyChangeStatusDTO) {
        HistoryChangeStatus historyChangeStatus = HistoryChangeStatusMapper.toEntity(historyChangeStatusDTO);
        HistoryChangeStatus savedHistoryChangeStatus = historyChangeStatusRepository.save(historyChangeStatus);
        return HistoryChangeStatusMapper.toDTO(savedHistoryChangeStatus);
    }

    @Override
    public HistoryChangeStatusDTO updateHistoryChangeStatus(Integer id, HistoryChangeStatusDTO historyChangeStatusDTO) {
        HistoryChangeStatus historyChangeStatus = historyChangeStatusRepository.findById(id)
                .orElseThrow();
        // Update fields as needed
        HistoryChangeStatus updatedHistoryChangeStatus = historyChangeStatusRepository.save(historyChangeStatus);
        return HistoryChangeStatusMapper.toDTO(updatedHistoryChangeStatus);
    }

    @Override
    public void deleteHistoryChangeStatus(Integer id) {
        historyChangeStatusRepository.deleteById(id);
    }
}
