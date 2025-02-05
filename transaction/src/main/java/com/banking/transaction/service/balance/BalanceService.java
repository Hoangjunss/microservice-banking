package com.banking.transaction.service.balance;

import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.entity.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BalanceService {
    List<BalanceDTO> getAllBalances();
    BalanceDTO getBalanceDTOById(Integer id);
    Balance getBalanceById(Integer id);
    Balance getBalanceByAccountId(Integer accountId);
    BalanceDTO createBalance(BalanceDTO balanceDTO);
    BalanceDTO updateBalance(BalanceDTO balanceDTO);
    void updateBalance(Balance updatedBalance);
    void deleteBalance(Integer id);

}
