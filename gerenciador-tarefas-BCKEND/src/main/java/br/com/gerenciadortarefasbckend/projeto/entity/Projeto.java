package br.com.gerenciadortarefasbckend.projeto.entity;

import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Define a classe como uma entidade JPA, mapeada para a tabela "projetos" no banco de dados
@Entity
@Table(name = "projetos")
@Data // Anotação do Lombok que gera getters, setters, toString, equals e hashCode automaticamente
public class Projeto {

    // Identificador único do projeto, gerado automaticamente pelo banco
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Título do projeto, armazenado como uma coluna no banco
    private String titulo;

    // Descrição do projeto, para detalhar seu propósito ou objetivos
    private String descricao;

    // Data de entrega do projeto, armazenada como uma coluna "data_entrega"
    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    // Status do projeto (ex.: "Em andamento", "Concluído"), armazenado como texto
    private String status;

    // Relacionamento muitos-para-muitos com a entidade User, representando os participantes do projeto
    @ManyToMany
    @JoinTable(
            name = "projeto_participantes", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "projeto_id"), // Coluna que referencia o projeto
            inverseJoinColumns = @JoinColumn(name = "participantes") // Coluna que referencia os usuários
    )
    private List<User> participantes = new ArrayList<>(); // Lista inicializada para evitar NullPointerException

    // Relacionamento um-para-muitos com a entidade Tarefa, onde um projeto pode ter várias tarefas
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas = new ArrayList<>(); // Lista inicializada para armazenar as tarefas do projeto
}