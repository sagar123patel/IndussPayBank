package com.IndussPayBank.IndussPayBank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IndussPayBank.IndussPayBank.modal.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);
}
