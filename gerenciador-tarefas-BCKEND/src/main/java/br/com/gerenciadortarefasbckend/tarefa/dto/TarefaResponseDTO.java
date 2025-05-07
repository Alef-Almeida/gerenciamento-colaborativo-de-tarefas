package br.com.gerenciadortarefasbckend.tarefa.dto;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private boolean finalizado;
    private UserResponseDTO user;
    private ProjetoResponseDTO projeto;

    public TarefaResponseDTO(Long id, String titulo, String descricao, LocalDate data, boolean finalizado, UserResponseDTO user, ProjetoResponseDTO projeto) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = data;
        this.finalizado = finalizado;
        this.user = user;
        this.projeto = projeto;
    }

    public TarefaResponseDTO() {}
}