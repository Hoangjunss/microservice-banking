package com.banking.transaction.mapper;

import com.banking.transaction.dto.TransactionDTO;
import com.banking.transaction.entity.StatusAction;
import com.banking.transaction.entity.StatusTransaction;
import com.banking.transaction.entity.Transaction;

public class TransactionMapper {
    public static TransactionDTO toDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setBalanceId(transaction.getBalanceId());
        dto.setCreateAt(transaction.getCreateAt());
        dto.setIdStatusAction(transaction.getStatusAction().getId());
        dto.setIdStatusTransaction(transaction.getStatusTransaction().getId());
        return dto;
    }

    public static Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());
        transaction.setBalanceId(dto.getBalanceId());
        transaction.setCreateAt(dto.getCreateAt());

        if(dto.getIdStatusAction() != null) {
            StatusAction statusAction = new StatusAction();
            statusAction.setId(dto.getIdStatusAction());
            transaction.setStatusAction(statusAction);
        }

        if(dto.getIdStatusTransaction() != null) {
            StatusTransaction statusTransaction = new StatusTransaction();
            statusTransaction.setId(dto.getIdStatusTransaction());
            transaction.setStatusTransaction(statusTransaction);
        }
        return transaction;
    }
}
