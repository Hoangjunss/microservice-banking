package com.banking.transaction.repository;

import com.banking.transaction.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer,Integer> {
}
