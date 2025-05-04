package br.com.gerenciadortarefasbckend.controller;



import br.com.gerenciadortarefasbckend.entity.Tarefa;
import br.com.gerenciadortarefasbckend.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Tarefa> listar() {
        return service.listar();
    }

    @PostMapping
    public Tarefa create(@RequestBody Tarefa tarefa) {
        return service.save(tarefa);
    }

    @GetMapping("/{id}")
    public Tarefa buscar(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return service.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
