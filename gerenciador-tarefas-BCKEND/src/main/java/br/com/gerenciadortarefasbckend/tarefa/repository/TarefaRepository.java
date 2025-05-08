package br.com.gerenciadortarefasbckend.tarefa.repository;

import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Define a interface como um repositório JPA
@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    // Herda métodos CRUD padrão

    // Busca tarefas com base no status de finalização
    List<Tarefa> findByfinalizado(boolean finalizado);
}