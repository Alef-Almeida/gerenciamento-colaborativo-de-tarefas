package br.com.gerenciadortarefasbckend.tarefa.controller;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.tarefa.mapper.TarefaMapper;
import br.com.gerenciadortarefasbckend.tarefa.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    private final TarefaService service;
    private final TarefaMapper mapper;

    public TarefaController(TarefaService service) {
        this.service = service;
        this.mapper = new TarefaMapper(); // ou usar @Component e @Autowired
    }

    @GetMapping
    public List<TarefaResponseDTO> listar() {
        return service.listar()
                .stream()
                .map(mapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public TarefaResponseDTO create(@RequestBody TarefaRequestDTO dto) {
        Tarefa tarefa = mapper.toEntity(dto);
        return mapper.toResponseDTO(service.save(tarefa));
    }

    @GetMapping("/{id}")
    public TarefaResponseDTO buscar(@PathVariable Long id) {
        return mapper.toResponseDTO(service.findById(id));
    }

    @PutMapping("/{id}")
    public TarefaResponseDTO atualizar(@PathVariable Long id, @RequestBody TarefaRequestDTO dto) {
        Tarefa tarefaAtualizada = mapper.toEntity(dto);
        return mapper.toResponseDTO(service.atualizar(id, tarefaAtualizada));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
