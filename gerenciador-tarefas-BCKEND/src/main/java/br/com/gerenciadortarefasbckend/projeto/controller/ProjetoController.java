package br.com.gerenciadortarefasbckend.projeto.controller;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoRequestDTO;
import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.service.ProjetoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define a classe como um controlador REST
@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    // Injeta o serviço de projetos via construtor
    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    // Retorna todos os projetos como uma lista de DTOs
    @GetMapping
    public List<ProjetoResponseDTO> getAll() {
        return service.getAll();
    }

    // Busca um projeto pelo ID e retorna seu DTO
    @GetMapping("/{id}")
    public ProjetoResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    // Cria um novo projeto a partir de um DTO recebido
    @PostMapping
    public ProjetoResponseDTO create(@RequestBody ProjetoRequestDTO dto) {
        return service.create(dto);
    }

    // Atualiza um projeto existente com base no ID e DTO fornecido
    @PutMapping("/{id}")
    public ProjetoResponseDTO update(@PathVariable Long id, @RequestBody ProjetoRequestDTO dto) {
        return service.update(id, dto);
    }

    // Exclui um projeto pelo ID
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}