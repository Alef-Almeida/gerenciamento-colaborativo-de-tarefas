package br.com.gerenciadortarefasbckend.projeto.controller;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoRequestDTO;
import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.service.ProjetoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService service;

    public ProjetoController(ProjetoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProjetoResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ProjetoResponseDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public ProjetoResponseDTO create(@RequestBody ProjetoRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public ProjetoResponseDTO update(@PathVariable Long id, @RequestBody ProjetoRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
