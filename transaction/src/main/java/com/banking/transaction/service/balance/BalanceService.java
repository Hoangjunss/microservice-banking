package com.banking.transaction.service.balance;

import com.banking.transaction.dto.BalanceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BalanceService {
    List<BalanceDTO> getAllBalances();
    BalanceDTO getBalanceById(Integer id);
    BalanceDTO createBalance(BalanceDTO balanceDTO);
    BalanceDTO updateBalance(BalanceDTO balanceDTO);
    void deleteBalance(Integer id);

}
