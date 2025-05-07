package br.com.gerenciadortarefasbckend.user.entity;

import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tarefa> tarefas = new ArrayList<>();

    @ManyToMany(mappedBy = "participantes")
    private List<Projeto> projetos = new ArrayList<>();
}