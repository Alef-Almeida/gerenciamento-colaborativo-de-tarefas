package br.com.gerenciadortarefasbckend.tarefa.controller;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.tarefa.mapper.TarefaMapper;
import br.com.gerenciadortarefasbckend.tarefa.service.TarefaService;
import br.com.gerenciadortarefasbckend.user.entity.User;
import br.com.gerenciadortarefasbckend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    private final TarefaService service;
    private final TarefaMapper mapper;
    private final UserService userService;

    public TarefaController(TarefaService service, TarefaMapper mapper, UserService userService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> create(@RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = mapper.toEntity(dto);
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            User user = userService.findById(dto.getUser().getId());
            tarefa.setUser(user);
        }
        Tarefa savedTarefa = service.save(tarefa);
        TarefaResponseDTO responseDTO = mapper.toResponseDTO(savedTarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listar() {
        List<TarefaResponseDTO> tarefas = service.listar()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscar(@PathVariable Long id) {
        Tarefa tarefa = service.findById(id);
        TarefaResponseDTO responseDTO = mapper.toResponseDTO(tarefa);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefaAtualizada = mapper.toEntity(dto);
        if (dto.getUser() != null && dto.getUser().getId() != null) {
            User user = userService.findById(dto.getUser().getId());
            tarefaAtualizada.setUser(user);
        }
        Tarefa updatedTarefa = service.atualizar(id, tarefaAtualizada);
        TarefaResponseDTO responseDTO = mapper.toResponseDTO(updatedTarefa);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}