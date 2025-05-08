package br.com.gerenciadortarefasbckend.tarefa.dto;


import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import lombok.Data;

import java.time.LocalDate;

// Define a classe como um DTO para saída de dados de tarefas
@Data // Gera getters, setters, toString, equals e hashCode automaticamente
public class TarefaResponseDTO {
    // Identificador único da tarefa
    private Long id;

    // Título da tarefa
    private String titulo;

    // Descrição da tarefa
    private String descricao;

    // Data de entrega da tarefa
    private LocalDate dataEntrega;

    // Indica se a tarefa está finalizada
    private boolean finalizado;

    // Usuário associado à tarefa, representado como DTO
    private UserResponseDTO user;

    // Projeto associado à tarefa, representado como DTO
    private ProjetoResponseDTO projeto;

    // Construtor completo para inicializar todos os campos
    public TarefaResponseDTO(Long id, String titulo, String descricao, LocalDate data, boolean finalizado, UserResponseDTO user, ProjetoResponseDTO projeto) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataEntrega = data;
        this.finalizado = finalizado;
        this.user = user;
        this.projeto = projeto;
    }

    // Construtor vazio para flexibilidade
    public TarefaResponseDTO() {}
}