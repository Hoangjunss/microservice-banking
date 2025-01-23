package com.banking.transaction.service.statusAction;

import com.banking.transaction.dto.StatusActionDTO;
import com.banking.transaction.entity.StatusAction;
import com.banking.transaction.mapper.StatusActionMapper;
import com.banking.transaction.repository.StatusActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusActionServiceImpl implements StatusActionService {
    @Autowired
    private StatusActionRepository statusActionRepository;
    @Override
    public List<StatusActionDTO> getAllStatusActions() {
        return statusActionRepository.findAll().stream()
                .map(StatusActionMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StatusActionDTO getStatusActionById(Integer id) {
        return statusActionRepository.findById(id)
                .map(StatusActionMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public StatusActionDTO createStatusAction(StatusActionDTO statusActionDTO) {
        StatusAction statusAction = StatusActionMapper.toEntity(statusActionDTO);
        StatusAction savedStatusAction = statusActionRepository.save(statusAction);
        return StatusActionMapper.toDTO(savedStatusAction);
    }

    @Override
    public StatusActionDTO updateStatusAction(StatusActionDTO statusActionDTO) {
        StatusAction statusAction = statusActionRepository.findById(statusActionDTO.getId())
                .orElseThrow();
        statusAction.setName(statusActionDTO.getName());
        StatusAction updatedStatusAction = statusActionRepository.save(statusAction);
        return StatusActionMapper.toDTO(updatedStatusAction);
    }

    @Override
    public void deleteStatusAction(Integer id) {
        statusActionRepository.deleteById(id);
    }

    public Integer getGenerationId() {
        UUID uuid = UUID.randomUUID();
        return (int) (uuid.getMostSignificantBits() & 0xFFFFFFFFL);
    }
}
