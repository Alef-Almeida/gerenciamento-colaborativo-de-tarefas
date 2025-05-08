package br.com.gerenciadortarefasbckend.projeto.mapper;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoRequestDTO;
import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

// Classe utilitária para mapeamento entre entidade Projeto e seus DTOs
public class ProjetoMapper {

    // Converte um ProjetoRequestDTO em uma entidade Projeto, associando participantes
    public static Projeto toEntity(ProjetoRequestDTO dto, List<User> participantes) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        projeto.setParticipantes(participantes);
        return projeto;
    }

    // Converte uma entidade Projeto em um ProjetoResponseDTO, incluindo participantes como DTOs
    public static ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setTitulo(projeto.getTitulo());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataEntrega(projeto.getDataEntrega());
        dto.setStatus(projeto.getStatus());
        // Mapeia lista de usuários para UserResponseDTO
        List<UserResponseDTO> participantes = projeto.getParticipantes().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getNome(), user.getEmail()))
                .collect(Collectors.toList());
        dto.setParticipantes(participantes);
        return dto;
    }

    // Atualiza uma entidade Projeto com dados de um ProjetoRequestDTO e nova lista de participantes
    public static void updateEntity(Projeto projeto, ProjetoRequestDTO dto, List<User> participantes) {
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        // Limpa e atualiza a lista de participantes
        projeto.getParticipantes().clear();
        projeto.getParticipantes().addAll(participantes);
    }
}