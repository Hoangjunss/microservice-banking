package com.banking.transaction.service.statusTransaction;

import com.banking.transaction.dto.StatusTransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusTransactionService {
    List<StatusTransactionDTO> getAllStatusTransactions();
    StatusTransactionDTO getStatusTransactionById(Integer id);
    StatusTransactionDTO createStatusTransaction(StatusTransactionDTO statusTransactionDTO);
    StatusTransactionDTO updateStatusTransaction(Integer id, StatusTransactionDTO statusTransactionDTO);
    void deleteStatusTransaction(Integer id);
}
