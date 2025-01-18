package com.banking.transaction.service.statusAction;

import com.banking.transaction.dto.StatusActionDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusActionService {
    List<StatusActionDTO> getAllStatusActions();
    StatusActionDTO getStatusActionById(Integer id);
    StatusActionDTO createStatusAction(StatusActionDTO statusActionDTO);
    StatusActionDTO updateStatusAction(StatusActionDTO statusActionDTO);
    void deleteStatusAction(Integer id);
}
