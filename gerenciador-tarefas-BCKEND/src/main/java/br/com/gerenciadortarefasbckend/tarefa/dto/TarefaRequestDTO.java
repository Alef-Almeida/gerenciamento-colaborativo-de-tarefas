package br.com.gerenciadortarefasbckend.tarefa.dto;

import java.time.LocalDate;

public class TarefaRequestDTO {
    private String titulo;
    private String descricao;
    private LocalDate data;
    private boolean finalizado;

    public TarefaRequestDTO() {}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }
}