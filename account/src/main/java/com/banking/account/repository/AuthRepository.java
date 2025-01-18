package com.banking.account.repository;

import com.banking.account.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Integer> {
    Optional<Auth> findByEmail(String email);
}
