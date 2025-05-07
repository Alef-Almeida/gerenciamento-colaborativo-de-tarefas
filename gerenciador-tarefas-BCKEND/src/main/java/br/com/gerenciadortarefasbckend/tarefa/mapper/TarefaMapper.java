package br.com.gerenciadortarefasbckend.tarefa.mapper;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class TarefaMapper {

    public Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setData(dto.getData());
        tarefa.setFinalizado(dto.isFinalizado());
        return tarefa;
    }

    public TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        UserResponseDTO userDTO = null;
        if (tarefa.getUser() != null) {
            userDTO = new UserResponseDTO(
                    tarefa.getUser().getId(),
                    tarefa.getUser().getNome(),
                    tarefa.getUser().getEmail()
            );
        }
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getData(),
                tarefa.isFinalizado(),
                userDTO
        );
    }
}