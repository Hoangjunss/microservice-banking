package com.banking.transaction.service.statusTransaction;

import com.banking.transaction.dto.StatusTransactionDTO;
import com.banking.transaction.entity.StatusTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusTransactionService {
    List<StatusTransactionDTO> getAllStatusTransactions();
    StatusTransactionDTO getStatusTransactionDTOById(Integer id);
    StatusTransaction getStatusTransactionById(Integer id);
    StatusTransactionDTO createStatusTransaction(StatusTransactionDTO statusTransactionDTO);
    StatusTransactionDTO updateStatusTransaction(StatusTransactionDTO statusTransactionDTO);
    void deleteStatusTransaction(Integer id);
}
