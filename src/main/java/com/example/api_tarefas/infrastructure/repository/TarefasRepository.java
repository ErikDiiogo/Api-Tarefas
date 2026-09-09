package com.example.api_tarefas.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.api_tarefas.infrastructure.entity.Tarefas;

public interface TarefasRepository extends JpaRepository<Tarefas, Long> {    
}
