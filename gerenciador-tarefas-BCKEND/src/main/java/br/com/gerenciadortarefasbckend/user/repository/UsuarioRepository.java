package br.com.gerenciadortarefasbckend.user.repository;

import br.com.gerenciadortarefasbckend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface de repositório para a entidade User
// Estende JpaRepository, fornecendo operações padrão como salvar, deletar, buscar por ID, etc.
public interface UsuarioRepository extends JpaRepository<User, Long> {

    // Método customizado para verificar se já existe um usuário com o email informado
    boolean existsByEmail(String email);
}
