package com.IndussPayBank.IndussPayBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.IndussPayBank.IndussPayBank.dto.UserDto;
import com.IndussPayBank.IndussPayBank.modal.User;
import com.IndussPayBank.IndussPayBank.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }
}