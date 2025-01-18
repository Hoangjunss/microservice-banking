package com.banking.transaction.mapper;

import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.entity.Balance;

public class BalanceMapper {
    public static BalanceDTO toDTO(Balance balance) {
        BalanceDTO dto = new BalanceDTO();
        dto.setId(balance.getId());
        dto.setBalance(balance.getBalance());
        dto.setAccountId(balance.getAccountId());
        dto.setCreateAt(balance.getCreateAt());
        dto.setStatus(balance.isStatus());
        return dto;
    }

    public static Balance toEntity(BalanceDTO dto) {
        Balance balance = new Balance();
        balance.setId(dto.getId());
        balance.setBalance(dto.getBalance());
        balance.setAccountId(dto.getAccountId());
        balance.setCreateAt(dto.getCreateAt());
        balance.setStatus(dto.isStatus());
        return balance;
    }
}
