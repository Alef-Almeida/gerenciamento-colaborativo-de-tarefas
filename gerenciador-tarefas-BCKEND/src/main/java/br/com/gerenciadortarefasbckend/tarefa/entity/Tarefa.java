package br.com.gerenciadortarefasbckend.tarefa.entity;

import br.com.gerenciadortarefasbckend.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "tarefas") // Garante que a tabela seja chamada "tarefas"
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDate data;
    private boolean finalizado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Tarefa() {
    }
}