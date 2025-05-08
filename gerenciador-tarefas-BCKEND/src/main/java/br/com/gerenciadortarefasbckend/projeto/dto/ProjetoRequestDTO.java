package br.com.gerenciadortarefasbckend.projeto.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

// Define a classe como um DTO para entrada de dados de projetos
@Data // Gera getters, setters, toString, equals e hashCode automaticamente
public class ProjetoRequestDTO {
    // Título do projeto
    private String titulo;

    // Descrição do projeto
    private String descricao;

    // Data de entrega do projeto
    private LocalDate dataEntrega;

    // Status do projeto (ex.: "Em andamento", "Concluído")
    private String status;

    // Lista de IDs dos participantes associados ao projeto
    private List<Long> participantes;
}