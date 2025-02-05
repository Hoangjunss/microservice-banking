package com.banking.transaction.service.transfer;

import com.banking.transaction.dto.TransferDTO;
import com.banking.transaction.entity.Transfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransferService {
    List<TransferDTO> getAllTransfers();
    TransferDTO getTransferDTOById(Integer id);
    Transfer getTransferById(Integer id);
    TransferDTO createTransfer(TransferDTO transferDTO);
    TransferDTO updateTransfer(TransferDTO transferDTO);
    void deleteTransfer(Integer id);
}
