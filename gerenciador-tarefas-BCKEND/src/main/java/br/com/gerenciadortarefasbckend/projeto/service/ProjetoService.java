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

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository userRepository;

    public List<ProjetoResponseDTO> getAll() {
        return projetoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public ProjetoResponseDTO findById(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + id));
        return toResponseDTO(projeto);
    }

    @Transactional
    public ProjetoResponseDTO create(ProjetoRequestDTO dto) {
        Projeto projeto = toEntity(dto);
        projeto = projetoRepository.save(projeto);
        return toResponseDTO(projeto);
    }

    @Transactional
    public ProjetoResponseDTO update(Long id, ProjetoRequestDTO dto) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + id));

        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());

        // Atualizar participantes
        List<User> participantes = userRepository.findAllById(dto.getParticipantes());
        projeto.getParticipantes().clear();
        projeto.getParticipantes().addAll(participantes);

        projeto = projetoRepository.save(projeto);
        return toResponseDTO(projeto);
    }

    @Transactional
    public void delete(Long id) {
        if (!projetoRepository.existsById(id)) {
            throw new RuntimeException("Projeto não encontrado: " + id);
        }
        projetoRepository.deleteById(id);
    }

    private Projeto toEntity(ProjetoRequestDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());

        // Buscar usuários pelos IDs
        if (dto.getParticipantes() != null) {
            List<User> participantes = userRepository.findAllById(dto.getParticipantes());
            projeto.setParticipantes(participantes);
        }

        return projeto;
    }

    private ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setTitulo(projeto.getTitulo());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataEntrega(projeto.getDataEntrega());
        dto.setStatus(projeto.getStatus());

        // Mapear participantes
        List<UserResponseDTO> participantes = projeto.getParticipantes().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getNome(), user.getEmail()))
                .collect(Collectors.toList());
        dto.setParticipantes(participantes);

        // Mapear tarefas
        List<TarefaResponseDTO> tarefas = projeto.getTarefas().stream()
                .map(this::toTarefaResponseDTO)
                .collect(Collectors.toList());
        dto.setTarefas(tarefas);

        return dto;
    }

    private TarefaResponseDTO toTarefaResponseDTO(Tarefa tarefa) {
        UserResponseDTO userDTO = tarefa.getUser() != null
                ? new UserResponseDTO(tarefa.getUser().getId(), tarefa.getUser().getNome(), tarefa.getUser().getEmail())
                : null;

        // Mapear projeto de forma simplificada para evitar ciclo
        ProjetoResponseDTO projetoDTO = tarefa.getProjeto() != null
                ? new ProjetoResponseDTO()
                : null;
        if (projetoDTO != null) {
            projetoDTO.setId(tarefa.getProjeto().getId());
            projetoDTO.setTitulo(tarefa.getProjeto().getTitulo());
            // Não incluir tarefas ou participantes para evitar recursão
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