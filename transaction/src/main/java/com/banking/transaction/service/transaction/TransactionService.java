package com.banking.transaction.service.transaction;

import com.banking.transaction.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {
    List<TransactionDTO> getAllTransactions();
    TransactionDTO getTransactionById(Integer id);
    TransactionDTO createTransaction(TransactionDTO transactionDTO);
    TransactionDTO updateTransaction(Integer id, TransactionDTO transactionDTO);
    void deleteTransaction(Integer id);
}
