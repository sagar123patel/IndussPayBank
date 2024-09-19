package com.IndussPayBank.IndussPayBank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.IndussPayBank.IndussPayBank.dto.UserDto;
import com.IndussPayBank.IndussPayBank.modal.Bank;
import com.IndussPayBank.IndussPayBank.modal.User;
import com.IndussPayBank.IndussPayBank.repository.BankRepository;
import com.IndussPayBank.IndussPayBank.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BankRepository bankRepository;
    
    @Transactional
    public User addUser(UserDto userDto) {
    	 // Check for duplicate email and phone number
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already taken.");
        }
        if (userRepository.existsByPhone(userDto.getPhone())) {
            throw new RuntimeException("Phone number is already taken.");
        }

        if (bankRepository.findByAccountNumber(userDto.getBank().getAccountNumber()).isPresent()) {
            throw new RuntimeException("Duplicate account number");
        }
        
        // Create new User entity and set fields
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhone(userDto.getPhone());

        // Map BankDto to Bank entity and link to User
        if (userDto.getBank() != null) {
            Bank bank = new Bank();
            bank.setBankName(userDto.getBank().getBankName());
            bank.setBankIfsc(userDto.getBank().getBankIfsc());
            bank.setAccountNumber(userDto.getBank().getAccountNumber());

            // Set the user reference in the bank
            bank.setUser(user);
            user.setBank(bank);
        }

        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}