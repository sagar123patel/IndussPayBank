package com.IndussPayBank.IndussPayBank.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IndussPayBank.IndussPayBank.dto.TxnDto;
import com.IndussPayBank.IndussPayBank.exception.UserNotFoundException;
import com.IndussPayBank.IndussPayBank.modal.Txn;
import com.IndussPayBank.IndussPayBank.modal.User;
import com.IndussPayBank.IndussPayBank.repository.TxnRepository;
import com.IndussPayBank.IndussPayBank.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TxnService {

	@Autowired
	private TxnRepository txnRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Txn addTransaction(Long userId, TxnDto txnDto) {
		// Fetch the user by userId, throw exception if not found
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		// Validate the amount must be non-negative
		if (txnDto.getAmount() < 0) {
			throw new RuntimeException("Transaction amount must be non-negative.");
		}

		// Map the TxnDto to Txn entity
		Txn txn = new Txn();
		txn.setCustomerName(txnDto.getCustomerName());
		txn.setService(txnDto.getService());
		txn.setAmount(txnDto.getAmount());
		txn.setGst(txnDto.getGst());
		txn.setCommission(txnDto.getCommission());

		// Generate a random transaction ID (You can use UUID for a unique txnId)
		txn.setTxnId(UUID.randomUUID().toString());

		// Link the transaction to the user
		txn.setUser(user);

		return txnRepository.save(txn);
	}

	public List<Txn> getTxnsInRange(Long userId, double minAmount, double maxAmount) {
		if (userId != null) {
	        if (!userRepository.existsById(userId)) {
	            throw new UserNotFoundException("User not found");
	        }
	        return txnRepository.findByUserUserIdAndAmountBetween(userId, minAmount, maxAmount);
	    }
	    return txnRepository.findByAmountBetween(minAmount, maxAmount);
	}

	public List<Txn> sortTxnsByAmount() {
		return txnRepository.findAllByOrderByAmountAsc();
	}

	public List<Txn> getAllTransactions() {
		return txnRepository.findAll();
	}

	public List<Txn> getTxnsByUserIds(List<Long> userIds) {
		return txnRepository.findByUserUserIdIn(userIds);
	}
}