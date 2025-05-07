package br.com.gerenciadortarefasbckend.user.repository;

import br.com.gerenciadortarefasbckend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
