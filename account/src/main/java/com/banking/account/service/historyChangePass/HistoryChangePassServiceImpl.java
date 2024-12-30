package com.banking.account.service.historyChangePass;
import com.banking.account.entity.HistoryChangePass;
import com.banking.account.dto.HistoryChangePassDTO;
import com.banking.account.mapper.HistoryChangePassMapper;
import com.banking.account.repository.HistoryChangePassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryChangePassServiceImpl implements HistoryChangePassService {

    @Autowired
    private HistoryChangePassRepository historyChangePassRepository;

    @Override
    public List<HistoryChangePassDTO> getAllHistoryChangePasses() {
        return historyChangePassRepository.findAll().stream()
                .map(HistoryChangePassMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoryChangePassDTO getHistoryChangePassById(Integer id) {
        return historyChangePassRepository.findById(id)
                .map(HistoryChangePassMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public HistoryChangePassDTO createHistoryChangePass(HistoryChangePassDTO historyChangePassDTO) {
        HistoryChangePass historyChangePass = HistoryChangePassMapper.toEntity(historyChangePassDTO);
        HistoryChangePass savedHistoryChangePass = historyChangePassRepository.save(historyChangePass);
        return HistoryChangePassMapper.toDTO(savedHistoryChangePass);
    }

    @Override
    public HistoryChangePassDTO updateHistoryChangePass(Integer id, HistoryChangePassDTO historyChangePassDTO) {
        HistoryChangePass historyChangePass = historyChangePassRepository.findById(id)
                .orElseThrow();
        historyChangePass.setOldPass(historyChangePassDTO.getOldPass());
        historyChangePass.setNewPass(historyChangePassDTO.getNewPass());
        HistoryChangePass updatedHistoryChangePass = historyChangePassRepository.save(historyChangePass);
        return HistoryChangePassMapper.toDTO(updatedHistoryChangePass);
    }

    @Override
    public void deleteHistoryChangePass(Integer id) {
        historyChangePassRepository.deleteById(id);
    }
}
