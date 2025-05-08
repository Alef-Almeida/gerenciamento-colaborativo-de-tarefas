package br.com.gerenciadortarefasbckend.projeto.repository;

import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

// Define a interface como um repositório JPA para a entidade Projeto
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
    // Herda métodos CRUD padrão (ex.: save, findById, findAll, delete) para Projeto
}