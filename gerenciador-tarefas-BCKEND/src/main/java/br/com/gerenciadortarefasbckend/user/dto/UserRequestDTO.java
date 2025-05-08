package br.com.gerenciadortarefasbckend.user.dto;

import lombok.Data;

// Define a classe como um DTO para entrada de dados de usuários
@Data // Gera getters, setters, toString, equals e hashCode automaticamente
public class UserRequestDTO {
    // Identificador único do usuário (pode ser nulo para criação)
    private Long id;

    // Nome do usuário
    private String nome;

    // Email do usuário
    private String email;

    // Senha do usuário (usada para autenticação)
    private String senha;

    // Construtor vazio para flexibilidade (ex.: serialização)
    public UserRequestDTO() {}

    // Construtor completo para inicializar todos os campos
    public UserRequestDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}