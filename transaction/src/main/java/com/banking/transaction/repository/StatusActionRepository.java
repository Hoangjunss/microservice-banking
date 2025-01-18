package com.banking.transaction.repository;

import com.banking.transaction.entity.StatusAction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusActionRepository extends JpaRepository<StatusAction,Integer> {
}
