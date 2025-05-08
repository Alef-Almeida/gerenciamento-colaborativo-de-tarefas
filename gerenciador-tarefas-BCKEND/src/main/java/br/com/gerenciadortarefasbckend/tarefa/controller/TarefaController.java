package br.com.gerenciadortarefasbckend.tarefa.controller;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define a classe como um controlador REST
@RequestMapping("/api/tarefas")
public class TarefaController {

    // Injeta o serviço de tarefas
    @Autowired
    private TarefaService service;

    // Lista todas as tarefas, retornando DTOs
    @GetMapping
    public List<TarefaResponseDTO> getAll() {
        return service.getAll();
    }

    // Busca uma tarefa por ID, retornando seu DTO
    @GetMapping("/{id}")
    public TarefaResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Cria uma nova tarefa a partir de um DTO, retornando status 201
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> create(@RequestBody TarefaRequestDTO dto) {
        TarefaResponseDTO responseDTO = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Atualiza uma tarefa existente com base no ID e DTO
    @PutMapping("/{id}")
    public TarefaResponseDTO update(@PathVariable Long id, @RequestBody TarefaRequestDTO dto) {
        return service.update(id, dto);
    }

    // Exclui uma tarefa por ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}