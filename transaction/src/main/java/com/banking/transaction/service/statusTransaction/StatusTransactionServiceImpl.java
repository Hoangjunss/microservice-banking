package com.banking.transaction.service.statusTransaction;

import com.banking.transaction.dto.StatusTransactionDTO;
import com.banking.transaction.entity.StatusTransaction;
import com.banking.transaction.mapper.StatusTransactionMapper;
import com.banking.transaction.repository.StatusTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusTransactionServiceImpl implements StatusTransactionService {
    @Autowired
    private StatusTransactionRepository statusTransactionRepository;
    @Override
    public List<StatusTransactionDTO> getAllStatusTransactions() {
        return statusTransactionRepository.findAll().stream()
                .map(StatusTransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StatusTransactionDTO getStatusTransactionById(Integer id) {
        return statusTransactionRepository.findById(id)
                .map(StatusTransactionMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public StatusTransactionDTO createStatusTransaction(StatusTransactionDTO statusTransactionDTO) {
        StatusTransaction statusTransaction = StatusTransactionMapper.toEntity(statusTransactionDTO);
        StatusTransaction savedStatusTransaction = statusTransactionRepository.save(statusTransaction);
        return StatusTransactionMapper.toDTO(savedStatusTransaction);
    }

    @Override
    public StatusTransactionDTO updateStatusTransaction(StatusTransactionDTO statusTransactionDTO) {
        StatusTransaction statusTransaction = statusTransactionRepository.findById(statusTransactionDTO.getId())
                .orElseThrow();

        statusTransaction.setName(statusTransactionDTO.getName());
        StatusTransaction updatedStatusTransaction = statusTransactionRepository.save(statusTransaction);
        return StatusTransactionMapper.toDTO(updatedStatusTransaction);
    }

    @Override
    public void deleteStatusTransaction(Integer id) {
        statusTransactionRepository.deleteById(id);
    }
}
