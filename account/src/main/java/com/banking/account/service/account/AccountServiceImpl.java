package com.banking.account.service.account;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;
import com.banking.account.mapper.AccountMapper;
import com.banking.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<AccountDTO> getAllAccountDTOs() {
        return accountRepository.findAll().stream()
                .map(AccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public AccountDTO getAccountDTOById(Integer id) {
        return accountRepository.findById(id)
                .map(AccountMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public Account getAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow();
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.toEntity(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.toDTO(savedAccount);
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public AccountDTO updateAccount(Integer id, AccountDTO accountDTO) {
        Account account = accountRepository.findById(id)
                .orElseThrow();

        // Update other fields as needed
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.toDTO(updatedAccount);
    }

    @Override
    public Account updateAccount(Integer id, Account updatedAccount) {
        Account account = accountRepository.findById(id)
                .orElseThrow();

        // Update other fields as needed
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }
}
