package com.banking.transaction.service.statusAction;

import com.banking.transaction.dto.StatusActionDTO;
import com.banking.transaction.entity.StatusAction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StatusActionService {
    List<StatusActionDTO> getAllStatusActions();
    StatusActionDTO getStatusActionDTOById(Integer id);
    StatusAction getStatusActionById(Integer id);
    StatusActionDTO createStatusAction(StatusActionDTO statusActionDTO);
    StatusActionDTO updateStatusAction(StatusActionDTO statusActionDTO);
    void deleteStatusAction(Integer id);
}
