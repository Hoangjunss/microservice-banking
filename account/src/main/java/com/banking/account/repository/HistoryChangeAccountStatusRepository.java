package com.banking.account.repository;

import com.banking.account.entity.HistoryChangeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryChangeAccountStatusRepository extends JpaRepository<HistoryChangeStatus,Integer> {
}
