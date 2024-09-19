package com.IndussPayBank.IndussPayBank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IndussPayBank.IndussPayBank.modal.Txn;

public interface TxnRepository extends JpaRepository<Txn, Long> {

	List<Txn> findByUserUserIdAndAmountBetween(Long userId, double minAmount, double maxAmount);

	List<Txn> findByAmountBetween(double minAmount, double maxAmount);
	
	List<Txn> findAllByOrderByAmountAsc();
	
	List<Txn> findByUserUserIdIn(List<Long> userIds);
}
