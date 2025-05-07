package br.com.gerenciadortarefasbckend.tarefa.service;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.tarefa.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<TarefaResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public TarefaResponseDTO create(TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return toResponseDTO(repository.save(tarefa));
    }

    public TarefaResponseDTO findById(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return toResponseDTO(tarefa);
    }

    public TarefaResponseDTO update(Long id, TarefaRequestDTO dto) {
        Tarefa tarefaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefaExistente.setTitulo(dto.getTitulo());
        tarefaExistente.setDescricao(dto.getDescricao());
        tarefaExistente.setData(dto.getDataEntrega());
        tarefaExistente.setFinalizado(dto.isFinalizado());

        return toResponseDTO(repository.save(tarefaExistente));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Conversão: DTO para Entidade
    private Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setData(dto.getDataEntrega());
        tarefa.setFinalizado(dto.isFinalizado());
        return tarefa;
    }

    // Conversão: Entidade para DTO
    private TarefaResponseDTO toResponseDTO(Tarefa tarefa) {
        TarefaResponseDTO dto = new TarefaResponseDTO();
        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setDataEntrega(tarefa.getData());
        dto.setFinalizado(tarefa.isFinalizado());
        return dto;
    }
}
