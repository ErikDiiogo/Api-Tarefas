package com.example.api_tarefas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.api_tarefas.infrastructure.entity.Tarefas;
import com.example.api_tarefas.service.TarefasService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor 
@RequestMapping ("/tarefas")
public class TarefasController {
    
    private final TarefasService tarefasService;

    @PostMapping 
    public ResponseEntity<Tarefas> salvarTarefas(@RequestBody Tarefas tarefas, Authentication authentication){
        return ResponseEntity.accepted().body(tarefasService.salvarTarefas(tarefas, authentication.getName()));
    }

    @GetMapping 
    public ResponseEntity<List<Tarefas>> listarTarefas(Authentication authentication){
        return ResponseEntity.ok().body(tarefasService.buscarTarefas(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefas> listarTarefasId(@PathVariable Long id){
        return tarefasService.buscarTarefasId(id)
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefas> altualizarTarefa(@RequestBody Tarefas tarefas, @PathVariable Long id) {
        return ResponseEntity.accepted().body(tarefasService.atualizarTarefas(id, tarefas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable Long id){
        tarefasService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
