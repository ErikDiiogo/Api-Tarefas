package com.example.api_tarefas.controller;

import com.example.api_tarefas.infrastructure.repository.UserRepository;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_tarefas.infrastructure.entity.User;
import com.example.api_tarefas.service.AuthenticationService;

@RestController 
public class AuthenticationController {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping ("/authenticate")

    public String authenticate(Authentication authentication){
        return authenticationService.authenticate(authentication);
    }

    @PostMapping ("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request){
        User user = new User();
        user.setUsername(request.get("username"));
        user.setPassword(passwordEncoder.encode(request.get("password")));
        userRepository.save(user);
        return ResponseEntity.ok("Usuario criado com sucesso");
    }
}
