package br.com.gerenciadortarefasbckend.projeto.dto;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjetoResponseDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private String status;
    private List<UserResponseDTO> participantes;
    private List<TarefaResponseDTO> tarefas;
}