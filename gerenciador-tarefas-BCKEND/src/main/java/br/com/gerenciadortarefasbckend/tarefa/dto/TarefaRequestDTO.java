package br.com.gerenciadortarefasbckend.tarefa.dto;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaRequestDTO {
    private String titulo;
    private String descricao;
    private LocalDate data;
    private boolean finalizado;
    private UserRequestDTO user;

    public TarefaRequestDTO() {}
}