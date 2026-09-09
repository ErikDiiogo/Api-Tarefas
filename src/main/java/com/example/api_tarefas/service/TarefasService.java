package com.example.api_tarefas.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.api_tarefas.infrastructure.entity.Tarefas;
import com.example.api_tarefas.infrastructure.repository.TarefasRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class TarefasService {

    private final TarefasRepository tarefasRepository;

    public Tarefas salvarTarefas(Tarefas tarefas){
        tarefas.setDataCriacao(LocalDateTime.now());
        return tarefasRepository.save(tarefas);
    }

    public List<Tarefas> buscarTarefas(){
        return tarefasRepository.findAll();
    }

    public Optional<Tarefas> buscarTarefasId(Long id){
        return tarefasRepository.findById(id);
    }

    public void deletarTarefa(Long id){
        tarefasRepository.deleteById(id);
    }

    public Tarefas atualizarTarefas(Long id, Tarefas tarefas){

        Tarefas tarefaExistente = tarefasRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Horario não está preenchido"));
        tarefas.setId(tarefaExistente.getId());
        return tarefasRepository.save(tarefas);
    }
}