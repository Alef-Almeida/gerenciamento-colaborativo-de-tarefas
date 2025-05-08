package br.com.gerenciadortarefasbckend.tarefa.service;

import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaRequestDTO;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.tarefa.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Define a classe como um serviço Spring
@Service
public class TarefaService {

    // Injeta o repositório de tarefas via construtor
    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    // Lista todas as tarefas, convertendo para DTOs
    public List<TarefaResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Cria uma nova tarefa a partir de um DTO e salva no banco
    public TarefaResponseDTO create(TarefaRequestDTO dto) {
        Tarefa tarefa = toEntity(dto);
        return toResponseDTO(repository.save(tarefa));
    }

    // Busca uma tarefa por ID, lançando exceção se não encontrada
    public TarefaResponseDTO findById(Long id) {
        Tarefa tarefa = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return toResponseDTO(tarefa);
    }

    // Atualiza uma tarefa existente com base no ID e DTO
    public TarefaResponseDTO update(Long id, TarefaRequestDTO dto) {
        Tarefa tarefaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaExistente.setTitulo(dto.getTitulo());
        tarefaExistente.setDescricao(dto.getDescricao());
        tarefaExistente.setData(dto.getDataEntrega());
        tarefaExistente.setFinalizado(dto.isFinalizado());
        return toResponseDTO(repository.save(tarefaExistente));
    }

    // Exclui uma tarefa por ID
    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Converte um DTO de entrada em uma entidade Tarefa
    private Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setData(dto.getDataEntrega());
        tarefa.setFinalizado(dto.isFinalizado());
        return tarefa;
    }

    // Converte uma entidade Tarefa em um DTO de resposta
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