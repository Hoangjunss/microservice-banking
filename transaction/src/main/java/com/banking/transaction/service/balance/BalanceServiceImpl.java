package com.banking.transaction.service.balance;

import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.entity.Balance;
import com.banking.transaction.mapper.BalanceMapper;
import com.banking.transaction.repository.BalanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BalanceServiceImpl implements  BalanceService {
    @Autowired
    private BalanceRepository balanceRepository;
    @Override
    public List<BalanceDTO> getAllBalances() {
        return balanceRepository.findAll().stream()
                .map(BalanceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BalanceDTO getBalanceById(Integer id) {
        return balanceRepository.findById(id)
                .map(BalanceMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public BalanceDTO createBalance(BalanceDTO balanceDTO) {
        Balance balance = BalanceMapper.toEntity(balanceDTO);
        Balance savedBalance = balanceRepository.save(balance);
        return BalanceMapper.toDTO(savedBalance);
    }

    @Override
    public BalanceDTO updateBalance(Integer id, BalanceDTO balanceDTO) {
        Balance balance = balanceRepository.findById(id)
                .orElseThrow();

        balance.setBalance(balanceDTO.getBalance());
        Balance updatedBalance = balanceRepository.save(balance);
        return BalanceMapper.toDTO(updatedBalance);
    }

    @Override
    public void deleteBalance(Integer id) {
        balanceRepository.deleteById(id);
    }
}
