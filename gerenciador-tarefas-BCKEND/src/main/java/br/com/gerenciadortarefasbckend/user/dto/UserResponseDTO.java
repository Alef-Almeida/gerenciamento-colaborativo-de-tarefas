package br.com.gerenciadortarefasbckend.user.dto;

import lombok.Data;

// Define a classe como um DTO para saída de dados de usuários
@Data // Lombok: gera automaticamente getters, setters, toString, equals e hashCode
public class UserResponseDTO {

    // Identificador único do usuário (geralmente atribuído pelo banco de dados)
    private Long id;

    // Nome do usuário
    private String nome;

    // Email do usuário
    private String email;

    // Construtor completo para inicializar todos os campos
    public UserResponseDTO(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Construtor vazio necessário para frameworks de serialização/deserialização (ex.: Jackson)
    public UserResponseDTO() {}
}
