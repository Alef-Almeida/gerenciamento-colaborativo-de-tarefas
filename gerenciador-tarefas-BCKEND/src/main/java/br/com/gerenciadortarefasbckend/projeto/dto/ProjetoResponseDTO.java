package br.com.gerenciadortarefasbckend.projeto.dto;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

// Define a classe como um DTO para saída de dados de projetos
@Data // Gera getters, setters, toString, equals e hashCode automaticamente
public class ProjetoResponseDTO {
    // Identificador único do projeto
    private Long id;

    // Título do projeto
    private String titulo;

    // Descrição do projeto
    private String descricao;

    // Data de entrega do projeto
    private LocalDate dataEntrega;

    // Status do projeto (ex.: "Em andamento", "Concluído")
    private String status;

    // Lista de participantes do projeto, representados como DTOs
    private List<UserResponseDTO> participantes;

    // Lista de tarefas associadas ao projeto, representadas como DTOs
    private List<TarefaResponseDTO> tarefas;
}