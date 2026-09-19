package com.example.api_tarefas.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_tarefas.infrastructure.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

    Optional<User> findByUsername(String username);
}
