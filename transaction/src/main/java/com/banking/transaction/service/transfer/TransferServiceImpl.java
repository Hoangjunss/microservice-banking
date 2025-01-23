package com.banking.transaction.service.transfer;

import com.banking.transaction.dto.TransferDTO;
import com.banking.transaction.entity.Balance;
import com.banking.transaction.entity.StatusTransfer;
import com.banking.transaction.entity.Transfer;
import com.banking.transaction.mapper.TransferMapper;
import com.banking.transaction.repository.BalanceRepository;
import com.banking.transaction.repository.StatusTransferRepository;
import com.banking.transaction.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;
    private StatusTransferRepository statusTransferRepository;
    private BalanceRepository balanceRepository;
    @Override
    public List<TransferDTO> getAllTransfers() {
        return transferRepository.findAll().stream()
                .map(TransferMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TransferDTO getTransferById(Integer id) {
        return transferRepository.findById(id)
                .map(TransferMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public TransferDTO createTransfer(TransferDTO transferDTO) {
        Transfer transfer  = TransferMapper.toEntity(transferDTO);
        Transfer savedTransfer = transferRepository.save(transfer);
        return TransferMapper.toDTO(savedTransfer);
    }

    @Override
    public TransferDTO updateTransfer(TransferDTO transferDTO) {
        Transfer transfer = transferRepository.findById(transferDTO.getId())
                .orElseThrow();

        transfer.setIdAccountSend(transferDTO.getIdAccountSend());
        transfer.setIdAccountReceive(transferDTO.getIdAccountReceive());
        transfer.setCreateAt(transferDTO.getCreateAt());

        if(transferDTO.getIdStatusTransfer() != null) {
            StatusTransfer statusTransfer = statusTransferRepository.findById(transferDTO.getIdStatusTransfer())
                    .orElseThrow();
            transfer.setStatusTransfer(statusTransfer);
        }

        transfer.setBalance(transferDTO.getBalance());

        Transfer updatedTransfer = transferRepository.save(transfer);

        return TransferMapper.toDTO(updatedTransfer);
    }

    @Override
    public void deleteTransfer(Integer id) {
        transferRepository.deleteById(id);
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
