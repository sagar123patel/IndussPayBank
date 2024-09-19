package com.IndussPayBank.IndussPayBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.IndussPayBank.IndussPayBank.dto.TxnDto;
import com.IndussPayBank.IndussPayBank.exception.UserNotFoundException;
import com.IndussPayBank.IndussPayBank.modal.Txn;
import com.IndussPayBank.IndussPayBank.service.TxnService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/add/{userId}")
    public ResponseEntity<?> addTransaction(@PathVariable Long userId, @Valid @RequestBody TxnDto txnDto) {
        return ResponseEntity.ok(txnService.addTransaction(userId, txnDto));
    }
    
    @PostMapping("/get/details")
    public ResponseEntity<List<Txn>> getTxnDetails(@RequestBody List<Long> userIds) {
    	if (userIds == null || userIds.isEmpty()) {
            // Fetch transactions for all users if list is null or empty
            List<Txn> txns = txnService.getAllTransactions();
            return ResponseEntity.ok(txns);
        } else {
            List<Txn> txns = txnService.getTxnsByUserIds(userIds);
            return ResponseEntity.ok(txns);
        }
    }

    @GetMapping("/amount/{initial_range}/{final_range}")
    public ResponseEntity<?> getTxnsInRange(@PathVariable double initial_range, @PathVariable double final_range, @RequestParam Long userId) {
    	try {
            List<Txn> txns = txnService.getTxnsInRange(userId, initial_range, final_range);
            if (txns.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No transactions found in the given range.");
            }
            return ResponseEntity.ok(txns);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }

    @GetMapping("/sort/amount")
    public ResponseEntity<List<Txn>> sortByAmount() {
        return ResponseEntity.ok(txnService.sortTxnsByAmount());
    }
}