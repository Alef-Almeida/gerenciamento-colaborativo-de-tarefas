package br.com.gerenciadortarefasbckend.projeto.service;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoRequestDTO;
import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.projeto.repository.ProjetoRepository;
import br.com.gerenciadortarefasbckend.tarefa.dto.TarefaResponseDTO;
import br.com.gerenciadortarefasbckend.tarefa.entity.Tarefa;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;
import br.com.gerenciadortarefasbckend.user.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// Define a classe como um serviço Spring
@Service
public class ProjetoService {

    // Injeta o repositório de projetos
    @Autowired
    private ProjetoRepository projetoRepository;

    // Injeta o repositório de usuários
    @Autowired
    private UsuarioRepository userRepository;

    // Lista todos os projetos, convertendo para DTOs
    public List<ProjetoResponseDTO> getAll() {
        return projetoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Busca um projeto por ID, lançando exceção se não encontrado
    public ProjetoResponseDTO findById(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + id));
        return toResponseDTO(projeto);
    }

    // Cria um novo projeto a partir de um DTO, salvando no banco
    @Transactional
    public ProjetoResponseDTO create(ProjetoRequestDTO dto) {
        Projeto projeto = toEntity(dto);
        projeto = projetoRepository.save(projeto);
        return toResponseDTO(projeto);
    }

    // Atualiza um projeto existente, incluindo participantes
    @Transactional
    public ProjetoResponseDTO update(Long id, ProjetoRequestDTO dto) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + id));
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        // Atualiza participantes
        List<User> participantes = userRepository.findAllById(dto.getParticipantes());
        projeto.getParticipantes().clear();
        projeto.getParticipantes().addAll(participantes);
        projeto = projetoRepository.save(projeto);
        return toResponseDTO(projeto);
    }

    // Exclui um projeto por ID, verificando sua existência
    @Transactional
    public void delete(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado: " + id);
        }
        projetoRepository.deleteById(id);
    }

    // Converte um DTO de entrada em uma entidade Projeto
    private Projeto toEntity(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        // Busca usuários pelos IDs, se fornecidos
        if (dto.getParticipantes() != null) {
            List<User> participantes = userRepository.findAllById(dto.getParticipantes());
            projeto.setParticipantes(participantes);
        }
        return projeto;
    }

    // Converte uma entidade Projeto em um DTO de resposta
    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setTitulo(projeto.getTitulo());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataEntrega(projeto.getDataEntrega());
        dto.setStatus(projeto.getStatus());
        // Mapeia participantes para DTOs
        List<UserResponseDTO> participantes = projeto.getParticipantes().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getNome(), user.getEmail()))
                .collect(Collectors.toList());
        dto.setParticipantes(participantes);
        // Mapeia tarefas para DTOs
        List<TarefaResponseDTO> tarefas = projeto.getTarefas().stream()
                .map(this::toTarefaResponseDTO)
                .collect(Collectors.toList());
        dto.setTarefas(tarefas);
        return dto;
    }

    // Converte uma entidade Tarefa em um DTO de resposta
    private TarefaResponseDTO toTarefaResponseDTO(Tarefa tarefa) {
        // Mapeia usuário, se presente
        UserResponseDTO userDTO = tarefa.getUser() != null
                ? new UserResponseDTO(tarefa.getUser().getId(), tarefa.getUser().getNome(), tarefa.getUser().getEmail())
                : null;
        // Mapeia projeto de forma simplificada para evitar recursão
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