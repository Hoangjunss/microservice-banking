package com.banking.account.repository;

import com.banking.account.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Auth,Integer> {
}
