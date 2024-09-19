package com.IndussPayBank.IndussPayBank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IndussPayBank.IndussPayBank.modal.Bank;

public interface BankRepository extends JpaRepository<Bank, Long> {

	Optional<Bank> findByAccountNumber(String accountNumber);
}
