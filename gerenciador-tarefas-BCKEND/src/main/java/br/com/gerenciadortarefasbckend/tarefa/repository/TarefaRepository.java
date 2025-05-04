package br.com.gerenciadortarefasbckend.tarefa.repository;

import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByfinalizado(boolean finalizado);

}
