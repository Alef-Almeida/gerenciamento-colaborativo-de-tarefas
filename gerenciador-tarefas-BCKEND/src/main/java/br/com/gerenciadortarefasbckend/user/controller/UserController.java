package br.com.gerenciadortarefasbckend.user.controller;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import br.com.gerenciadortarefasbckend.user.mapper.UserMapper;
import br.com.gerenciadortarefasbckend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Define a classe como um controlador REST com mapeamento base "/api/users"
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem
public class UserController {

    // Injeta o serviço e o mapeador de usuários via construtor
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    // Cria um novo usuário a partir de um DTO, retornando status 201
    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        User savedUser = userService.save(user);
        UserResponseDTO responseDTO = userMapper.toResponseDTO(savedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Lista todos os usuários, retornando uma lista de DTOs
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listar() {
        List<UserResponseDTO> users = userService.listar();
        return ResponseEntity.ok(users);
    }

    // Busca um usuário por ID, retornando seu DTO
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscar(@PathVariable Long id) {
        UserResponseDTO responseDTO = userService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }
}