package com.banking.transaction.service.statusTransfer;

import com.banking.transaction.dto.StatusTransferDTO;
import com.banking.transaction.entity.StatusTransfer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusTransferService {
    List<StatusTransferDTO> getAllStatusTransfers();
    StatusTransferDTO getStatusTransferDTOById(Integer id);
    StatusTransfer getStatusTransferById(Integer id);
    StatusTransferDTO createStatusTransfer(StatusTransferDTO statusTransferDTO);
    StatusTransferDTO updateStatusTransfer(StatusTransferDTO statusTransferDTO);
    void deleteStatusTransfer(Integer id);
}
