package br.com.gerenciadortarefasbckend.tarefa.dto;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TarefaRequestDTO {
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private boolean finalizado;
    private UserRequestDTO user;

}