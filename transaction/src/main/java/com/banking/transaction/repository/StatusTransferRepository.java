package com.banking.transaction.repository;

import com.banking.transaction.entity.StatusTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTransferRepository extends JpaRepository<StatusTransfer,Integer> {
}
