package com.banking.transaction.repository;

import com.banking.transaction.entity.StatusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTransactionRepository extends JpaRepository<StatusTransaction,Integer> {
}
