package br.com.gerenciadortarefasbckend.projeto.repository;

import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
