package br.com.gerenciadortarefasbckend.user.service;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import br.com.gerenciadortarefasbckend.user.mapper.UserMapper;
import br.com.gerenciadortarefasbckend.user.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Define a classe como um serviço Spring, responsável pela lógica de negócios relacionada ao usuário
@Service
public class UserService {

    // Injeção do repositório para acesso ao banco de dados
    private final UsuarioRepository repository;

    // Injeção do mapper para conversão entre DTOs e entidades
    private final UserMapper userMapper;

    // Construtor para injeção de dependências
    public UserService(UsuarioRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    // Salva um novo usuário após verificar se o email já está em uso
    public User save(User user) {
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado.");
        }
        return repository.save(user);
    }

    // Retorna uma lista de todos os usuários cadastrados, convertidos para DTO de resposta
    public List<UserResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Busca um usuário pelo ID e retorna um DTO de resposta
    public UserResponseDTO buscarPorId(Long id) {
        return repository.findById(id)
                .map(userMapper::toResponseDTO)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    // Busca uma entidade User pelo ID (caso seja necessário trabalhar com a entidade completa)
    public User findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id " + id));
    }

    // Deleta um usuário pelo ID, após verificar sua existência
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para deletar.");
        }
        repository.deleteById(id);
    }
}
