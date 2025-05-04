package br.com.gerenciadortarefasbckend.tarefa.service;

import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;

import java.util.List;

public interface TarefaIService {
    List<Tarefa> listar();
    Tarefa save(Tarefa tarefa);
    Tarefa findById(Long id);
    Tarefa atualizar(Long id, Tarefa novaTarefa);
    void delete(Long id);
}
