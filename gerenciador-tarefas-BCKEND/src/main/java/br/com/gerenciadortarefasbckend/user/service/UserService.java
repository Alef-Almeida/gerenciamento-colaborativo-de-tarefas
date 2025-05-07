package br.com.gerenciadortarefasbckend.user.service;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import br.com.gerenciadortarefasbckend.user.mapper.UserMapper;
import br.com.gerenciadortarefasbckend.user.repository.UsuarioRepository;
import br.com.gerenciadortarefasbckend.user.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UsuarioRepository repository;
    private final UserMapper userMapper;

    public UserService(UsuarioRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public User save(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        return repository.save(user);
    }

    public List<UserResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(userMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id " + id));
    }

    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para deletar.");
        }
        repository.deleteById(id);
    }
}