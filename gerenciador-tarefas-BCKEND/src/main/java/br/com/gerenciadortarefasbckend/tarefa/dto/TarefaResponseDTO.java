package br.com.gerenciadortarefasbckend.tarefa.dto;

import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private boolean finalizado;
    private UserResponseDTO user;

    public TarefaResponseDTO(Long id, String titulo, String descricao, LocalDate data, boolean finalizado, UserResponseDTO user) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
        this.finalizado = finalizado;
        this.user = user;
    }

    public TarefaResponseDTO() {}
}