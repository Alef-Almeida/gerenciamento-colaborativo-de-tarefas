package br.com.gerenciadortarefasbckend.user.mapper;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import org.springframework.stereotype.Component;

// Define a classe como um componente gerenciado pelo Spring (pode ser injetado em outras classes)
@Component
public class UserMapper {

    // Converte um objeto UserRequestDTO em uma entidade User
    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNome(dto.getNome());
        user.setEmail(dto.getEmail());
        user.setSenha(dto.getSenha());
        return user;
    }

    // Converte uma entidade User em um objeto UserResponseDTO
    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getNome(),
                user.getEmail()
        );
    }
}
