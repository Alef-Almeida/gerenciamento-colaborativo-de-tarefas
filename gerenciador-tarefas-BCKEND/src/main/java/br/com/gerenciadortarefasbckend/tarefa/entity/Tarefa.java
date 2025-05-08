package br.com.gerenciadortarefasbckend.tarefa.entity;

import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

// Define a classe como uma entidade JPA, mapeada para a tabela "tarefas"
@Data // Gera getters, setters, toString, equals e hashCode
@Entity
@Table(name = "tarefas")
public class Tarefa {
    // Identificador único da tarefa, gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título da tarefa
    private String titulo;

    // Descrição da tarefa
    private String descricao;

    // Data associada à tarefa (ex.: entrega ou criação)
    private LocalDate data;

    // Indica se a tarefa está finalizada
    private boolean finalizado;

    // Relacionamento muitos-para-um com a entidade User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Relacionamento muitos-para-um com a entidade Projeto
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    // Construtor vazio para JPA
    public Tarefa() {
    }
}