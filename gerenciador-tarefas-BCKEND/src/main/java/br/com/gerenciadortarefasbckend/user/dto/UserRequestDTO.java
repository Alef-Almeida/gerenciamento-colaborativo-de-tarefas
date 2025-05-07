package br.com.gerenciadortarefasbckend.user.dto;

import lombok.Data;

@Data
public class UserRequestDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public UserRequestDTO() {}

    public UserRequestDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
}