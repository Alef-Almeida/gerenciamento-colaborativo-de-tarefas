package br.com.gerenciadortarefasbckend.tarefa.mapper;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;

public class TarefaMapper {

    public static Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setData(dto.getData());
        tarefa.setFinalizado(dto.isFinalizado());
        return tarefa;
    }

    public TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(
                tarefa.getId(),
                tarefa.getTitulo(),
                tarefa.getDescricao(),
                tarefa.getData(),
                tarefa.isFinalizado()
        );
    }
}
