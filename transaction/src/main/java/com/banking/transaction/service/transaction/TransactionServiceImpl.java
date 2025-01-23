package com.banking.transaction.service.transaction;

import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.entity.StatusAction;
import com.banking.transaction.entity.StatusTransaction;
import com.banking.transaction.entity.Transaction;
import com.banking.transaction.mapper.TransactionMapper;
import com.banking.transaction.repository.StatusActionRepository;
import com.banking.transaction.repository.StatusTransactionRepository;
import com.banking.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements  TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    private StatusActionRepository statusActionRepository;
    private StatusTransactionRepository statusTransactionRepository;
    @Override
    public List<TransactionDTO> getAllTransactions() {
        return transactionRepository.findAll().stream()
                .map(TransactionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransactionDTO getTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = TransactionMapper.toEntity(transactionDTO);
        Transaction savedTransaction = transactionRepository.save(transaction);
        return TransactionMapper.toDTO(savedTransaction);
    }

    @Override
    public TransactionDTO updateTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = transactionRepository.findById(transactionDTO.getId())
                .orElseThrow();

        transaction.setBalanceId(transactionDTO.getBalanceId());
        transaction.setCreateAt(transactionDTO.getCreateAt());

        if (transactionDTO.getIdStatusAction() != null) {
            StatusAction statusAction = statusActionRepository.findById(transactionDTO.getIdStatusAction())
                    .orElseThrow();
            transaction.setStatusAction(statusAction);
        }

        if (transactionDTO.getIdStatusTransaction() != null) {
            StatusTransaction statusTransaction = statusTransactionRepository.findById(transactionDTO.getIdStatusTransaction())
                    .orElseThrow();
            transaction.setStatusTransaction(statusTransaction);
        }
        Transaction updatedTransaction = transactionRepository.save(transaction);

        return TransactionMapper.toDTO(updatedTransaction);
    }

    @Override
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
