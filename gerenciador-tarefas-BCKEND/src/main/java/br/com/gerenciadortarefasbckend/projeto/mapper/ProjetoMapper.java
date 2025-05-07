package br.com.gerenciadortarefasbckend.projeto.mapper;

import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoRequestDTO;
import br.com.gerenciadortarefasbckend.projeto.dto.ProjetoResponseDTO;
import br.com.gerenciadortarefasbckend.projeto.entity.Projeto;
import br.com.gerenciadortarefasbckend.user.dto.UserResponseDTO;
import br.com.gerenciadortarefasbckend.user.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ProjetoMapper {

    public static Projeto toEntity(ProjetoRequestDTO dto, List<User> participantes) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        projeto.setParticipantes(participantes);
        return projeto;
    }

    public static ProjetoResponseDTO toResponseDTO(Projeto projeto) {
        ProjetoResponseDTO dto = new ProjetoResponseDTO();
        dto.setId(projeto.getId());
        dto.setTitulo(projeto.getTitulo());
        dto.setDescricao(projeto.getDescricao());
        dto.setDataEntrega(projeto.getDataEntrega());
        dto.setStatus(projeto.getStatus());
        List<UserResponseDTO> participantes = projeto.getParticipantes().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getNome(), user.getEmail()))
                .collect(Collectors.toList());
        dto.setParticipantes(participantes);

        return dto;
    }

    public static void updateEntity(Projeto projeto, ProjetoRequestDTO dto, List<User> participantes) {
        projeto.setTitulo(dto.getTitulo());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataEntrega(dto.getDataEntrega());
        projeto.setStatus(dto.getStatus());
        projeto.getParticipantes().clear();
        projeto.getParticipantes().addAll(participantes);
    }
}