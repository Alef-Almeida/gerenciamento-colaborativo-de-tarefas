package br.com.gerenciadortarefasbckend.projeto.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProjetoRequestDTO {
    private String titulo;
    private String descricao;
    private LocalDate dataEntrega;
    private String status;
    private List<Long> participantes;
}