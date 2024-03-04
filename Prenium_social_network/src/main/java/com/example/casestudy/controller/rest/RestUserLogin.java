package com.example.casestudy.controller.rest;

import com.example.casestudy.model.User;
import com.example.casestudy.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/user")
@RestController
public class RestUserLogin {
    private final UserRepository userRepository;
    @GetMapping
    public ResponseEntity<?> getUserLogin(Authentication authentication){
       User user= userRepository.findByUserName(authentication.getName());
       return ResponseEntity.ok(user);
    }
}
