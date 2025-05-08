package br.com.gerenciadortarefasbckend.tarefa.dto;

import br.com.gerenciadortarefasbckend.user.dto.UserRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Define a classe como um DTO para entrada de dados de tarefas
@NoArgsConstructor // Gera construtor sem argumentos
@AllArgsConstructor // Gera construtor com todos os argumentos
@Builder // Gera um padrão Builder para criação de instâncias
@Data // Gera getters, setters, toString, equals e hashCode
public class TarefaRequestDTO {
    // Título da tarefa
    private String titulo;

    // Descrição da tarefa
    private String descricao;

    // Data de entrega da tarefa
    private LocalDate dataEntrega;

    // Indica se a tarefa está finalizada
    private boolean finalizado;

    // Usuário associado à tarefa, representado como DTO
    private UserRequestDTO user;
}