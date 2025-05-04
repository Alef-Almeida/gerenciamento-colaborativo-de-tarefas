package br.com.gerenciadortarefasbckend.tarefa.service;

import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.tarefa.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService implements TarefaIService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<Tarefa> listar() {
        return repository.findAll();
    }

    public Tarefa save(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public Tarefa findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa novaTarefa) {
        Tarefa tarefaExistente = findById(id);
        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setDescricao(novaTarefa.getDescricao());
        tarefaExistente.setData(novaTarefa.getData());
        tarefaExistente.setFinalizado(novaTarefa.isFinalizado());
        return repository.save(tarefaExistente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
