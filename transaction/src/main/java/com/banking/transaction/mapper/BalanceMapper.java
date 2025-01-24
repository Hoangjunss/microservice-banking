package com.banking.transaction.mapper;

import com.banking.transaction.dto.BalanceDTO;
import com.banking.transaction.entity.Balance;

import java.util.UUID;

public class BalanceMapper {
    public static BalanceDTO toDTO(Balance balance) {
        BalanceDTO dto = new BalanceDTO();
        dto.setId(balance.getId());
        dto.setBalance(balance.getBalance());
        dto.setAccountId(balance.getAccountId());
        dto.setCreateAt(balance.getCreateAt());
        dto.setStatus(balance.getStatus());
        return dto;
    }

    public static Balance toEntity(BalanceDTO balanceDTO) {
        Balance balance = new Balance();
        balance.setId(balanceDTO.getId());
        balance.setBalance(balanceDTO.getBalance());
        balance.setAccountId(balanceDTO.getAccountId());
        balance.setCreateAt(balanceDTO.getCreateAt());
        balance.setStatus(balanceDTO.getStatus());
        return balance;
    }

    public static void  updateBalance(Balance balance, BalanceDTO balanceDTO) {
        balance.setBalance(balanceDTO.getBalance());
        balance.setAccountId(balanceDTO.getAccountId());
        balance.setCreateAt(balanceDTO.getCreateAt());
        balance.setStatus(balanceDTO.getStatus());
    }
}
