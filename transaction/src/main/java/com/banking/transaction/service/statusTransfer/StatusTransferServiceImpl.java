package com.banking.transaction.service.statusTransfer;

import com.banking.transaction.dto.StatusTransferDTO;
import com.banking.transaction.entity.StatusTransfer;
import com.banking.transaction.mapper.StatusTransferMapper;
import com.banking.transaction.repository.StatusTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusTransferServiceImpl implements  StatusTransferService {
    @Autowired
    private StatusTransferRepository statusTransferRepository;
    @Override
    public List<StatusTransferDTO> getAllStatusTransfers() {
        return statusTransferRepository.findAll().stream()
                .map(StatusTransferMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StatusTransferDTO getStatusTransferById(Integer id) {
        statusTransferRepository.findById(id)
                .map(StatusTransferMapper::toDTO)
                .orElseThrow();
        return null;
    }

    @Override
    public StatusTransferDTO createStatusTransfer(StatusTransferDTO statusTransferDTO) {
        StatusTransfer statusTransfer = StatusTransferMapper.toEntity(statusTransferDTO);
        StatusTransfer savedStatusTransfer = statusTransferRepository.save(statusTransfer);
        return StatusTransferMapper.toDTO(savedStatusTransfer);
    }

    @Override
    public StatusTransferDTO updateStatusTransfer(StatusTransferDTO statusTransferDTO) {
        StatusTransfer statusTransfer = statusTransferRepository.findById(statusTransferDTO.getId())
                .orElseThrow();

        statusTransfer.setName(statusTransferDTO.getName());
        StatusTransfer updatedStatusTransfer = statusTransferRepository.save(statusTransfer);
        return StatusTransferMapper.toDTO(updatedStatusTransfer);
    }

    @Override
    public void deleteStatusTransfer(Integer id) {
        statusTransferRepository.deleteById(id);
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
