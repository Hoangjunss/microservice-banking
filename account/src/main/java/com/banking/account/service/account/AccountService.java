package com.banking.account.service.account;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AccountService {
    List<AccountDTO> getAllAccountDTOs();
    List<Account> getAllAccounts();
    AccountDTO getAccountDTOById(Integer id);
    Account getAccountById(Integer id);
    AccountDTO createAccount(AccountDTO accountDTO);
    Account createAccount(Account account);
    AccountDTO updateAccount(Integer id, AccountDTO accountDTO);
    Account updateAccount(Integer id, Account updatedAccount);
    void deleteAccount(Integer id);
}
