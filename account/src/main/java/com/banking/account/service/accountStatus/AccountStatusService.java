package com.banking.account.service.accountStatus;

import com.banking.account.dto.AccountStatusDTO;

import java.util.List;

public interface AccountStatusService {
    List<AccountStatusDTO> getAllAccountStatuses();
    AccountStatusDTO getAccountStatusById(Integer id);
    AccountStatusDTO createAccountStatus(AccountStatusDTO accountStatusDTO);
    AccountStatusDTO updateAccountStatus(Integer id, AccountStatusDTO accountStatusDTO);
    void deleteAccountStatus(Integer id);
}
