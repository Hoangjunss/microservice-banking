package com.banking.transaction.repository;

import com.banking.transaction.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Integer> {
}
