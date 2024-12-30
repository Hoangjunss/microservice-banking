package com.banking.account.repository;

import com.banking.account.entity.HistoryChangePass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryChangePassRepository extends JpaRepository<HistoryChangePass,Integer> {
}
