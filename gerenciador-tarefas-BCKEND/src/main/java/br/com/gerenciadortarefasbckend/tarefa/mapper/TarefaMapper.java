package br.com.gerenciadortarefasbckend.tarefa.mapper;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;

public class TarefaMapper {

    public static Tarefa toEntity(TarefaRequestDTO dto, User user, Projeto projeto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setData(dto.getDataEntrega());
        tarefa.setFinalizado(dto.isFinalizado());
        tarefa.setUser(user);
        tarefa.setProjeto(projeto);
        return tarefa;
    }

    public static TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        UserResponseDTO userDTO = tarefa.getUser() != null
                ? new UserResponseDTO(tarefa.getUser().getId(), tarefa.getUser().getNome(), tarefa.getUser().getEmail())
                : null;

        ProjetoResponseDTO projetoDTO = tarefa.getProjeto() != null
                ? new ProjetoResponseDTO()
                : null;

        if (projetoDTO != null) {
            projetoDTO.setId(tarefa.getProjeto().getId());
            projetoDTO.setTitulo(tarefa.getProjeto().getTitulo());
        }

        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getData(),
                tarefa.isFinalizado(),
                userDTO,
                projetoDTO
        );
    }
}
