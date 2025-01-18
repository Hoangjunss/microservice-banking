package com.banking.transaction.mapper;

import com.banking.transaction.dto.TransferDTO;
import com.banking.transaction.entity.Balance;
import com.banking.transaction.entity.StatusTransfer;
import com.banking.transaction.entity.Transfer;

public class TransferMapper {
    public static TransferDTO toDTO(Transfer transfer) {
        TransferDTO dto = new TransferDTO();
        dto.setId(transfer.getId());
        dto.setIdAccountSend(transfer.getIdAccountSend());
        dto.setIdAccountReceive(transfer.getIdAccountReceive());
        dto.setBalance(transfer.getBalance());
        dto.setIdStatusTransfer(transfer.getStatusTransfer().getId());
        dto.setCreateAt(transfer.getCreateAt());
        return dto;
    }

    public static Transfer toEntity(TransferDTO dto) {
        Transfer transfer = new Transfer();
        transfer.setId(dto.getId());
        transfer.setIdAccountSend(dto.getIdAccountSend());
        transfer.setIdAccountReceive(dto.getIdAccountReceive());
        transfer.setBalance(dto.getBalance());

        if(dto.getIdStatusTransfer() != null) {
            StatusTransfer statusTransfer = new StatusTransfer();
            statusTransfer.setId(dto.getIdStatusTransfer());
            transfer.setStatusTransfer(statusTransfer);
        }
        transfer.setCreateAt(dto.getCreateAt());
        return transfer;
    }
}
