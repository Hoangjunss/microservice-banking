package com.banking.account.service.accountStatus;

import com.banking.account.dto.AccountStatusDTO;
import com.banking.account.mapper.AccountStatusMapper;
import com.banking.account.repository.AccountStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.banking.account.entity.AccountStatus;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    @Autowired
    private AccountStatusRepository accountStatusRepository;

    @Override
    public List<AccountStatusDTO> getAllAccountStatuses() {
        return accountStatusRepository.findAll().stream()
                .map(AccountStatusMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountStatusDTO getAccountStatusById(Integer id) {
        return accountStatusRepository.findById(id)
                .map(AccountStatusMapper::toDTO)
                .orElseThrow();
    }

    @Override
    public AccountStatusDTO createAccountStatus(AccountStatusDTO accountStatusDTO) {
        AccountStatus accountStatus = AccountStatusMapper.toEntity(accountStatusDTO);
        AccountStatus savedAccountStatus = accountStatusRepository.save(accountStatus);
        return AccountStatusMapper.toDTO(savedAccountStatus);
    }

    @Override
    public AccountStatusDTO updateAccountStatus(Integer id, AccountStatusDTO accountStatusDTO) {
        AccountStatus accountStatus = accountStatusRepository.findById(id)
                .orElseThrow();
        accountStatus.setNameAccountStatus(accountStatusDTO.getNameAccountStatus());
        AccountStatus updatedAccountStatus = accountStatusRepository.save(accountStatus);
        return AccountStatusMapper.toDTO(updatedAccountStatus);
    }

    @Override
    public void deleteAccountStatus(Integer id) {
        accountStatusRepository.deleteById(id);
    }
}
