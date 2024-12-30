package com.banking.account.mapper;

import com.banking.account.dto.AccountDTO;
import com.banking.account.entity.Account;

public class AccountMapper {
    public static AccountDTO toDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setId(account.getId());
        dto.setBalance(account.getBalance());
        dto.setAccountStatusId(account.getAccountStatus().getId());
        dto.setDateCreate(account.getDateCreate());
        return dto;
    }

    public static Account toEntity(AccountDTO dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setBalance(dto.getBalance());
        // You will need to fetch the AccountStatus entity by its id
        // account.setAccountStatus(accountStatus);
        account.setDateCreate(dto.getDateCreate());
        return account;
    }
}
