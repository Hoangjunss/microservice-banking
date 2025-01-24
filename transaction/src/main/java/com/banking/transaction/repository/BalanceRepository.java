package com.banking.transaction.repository;

import com.banking.transaction.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance,Integer> {
    Optional<Balance> findByAccountId(Integer accountId);
}
