package com.banking.transaction.service.statusTransfer;

import com.banking.transaction.dto.StatusTransferDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusTransferService {
    List<StatusTransferDTO> getAllStatusTransfers();
    StatusTransferDTO getStatusTransferById(Integer id);
    StatusTransferDTO createStatusTransfer(StatusTransferDTO statusTransferDTO);
    StatusTransferDTO updateStatusTransfer(Integer id, StatusTransferDTO statusTransferDTO);
    void deleteStatusTransfer(Integer id);
}
