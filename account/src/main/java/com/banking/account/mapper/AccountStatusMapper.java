package com.banking.account.mapper;

import com.banking.account.dto.AccountStatusDTO;
import com.banking.account.entity.AccountStatus;

public class AccountStatusMapper {
    public static AccountStatusDTO toDTO(AccountStatus accountStatus) {
        AccountStatusDTO dto = new AccountStatusDTO();
        dto.setId(accountStatus.getId());
        dto.setNameAccountStatus(accountStatus.getNameAccountStatus());
        return dto;
    }

    public static AccountStatus toEntity(AccountStatusDTO dto) {
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setId(dto.getId());
        accountStatus.setNameAccountStatus(dto.getNameAccountStatus());
        return accountStatus;
    }
}
