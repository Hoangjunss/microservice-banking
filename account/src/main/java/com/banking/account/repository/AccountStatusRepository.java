package com.banking.account.repository;

import com.banking.account.entity.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountStatusRepository extends JpaRepository<AccountStatus,Integer> {
}
