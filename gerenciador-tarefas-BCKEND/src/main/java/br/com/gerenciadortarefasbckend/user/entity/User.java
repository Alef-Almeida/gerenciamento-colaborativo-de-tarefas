package br.com.gerenciadortarefasbckend.user.entity;

import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

// Marca a classe como uma entidade JPA que será mapeada para uma tabela no banco de dados
@Entity

// Define o nome da tabela no banco de dados como "users"
@Table(name = "users")

// Lombok: gera automaticamente os métodos getters e setters
@Getter
@Setter

// Lombok: gera um construtor sem argumentos
@NoArgsConstructor

// Lombok: gera um construtor com todos os argumentos
@AllArgsConstructor

// Lombok: permite a criação de objetos usando o padrão Builder
@Builder
public class User {

    // Identificador único do usuário, gerado automaticamente pelo banco (auto incremento)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nome do usuário
    private String nome;

    // Email do usuário
    private String email;

    // Senha do usuário (armazenada de forma segura na base de dados)
    private String senha;

    // Lista de tarefas atribuídas ao usuário
    // mappedBy = "user": indica que o lado responsável pela associação é o atributo "user" em Tarefa
    // cascade = CascadeType.ALL: operações em User também afetam suas tarefas
    // orphanRemoval = true: tarefas removidas da lista serão excluídas do banco
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    // Relação muitos-para-muitos com projetos
    // mappedBy = "participantes": o controle da relação está na entidade Projeto
    @ManyToMany(mappedBy = "participantes")
    private List<Projeto> projetos = new ArrayList<>();
}
