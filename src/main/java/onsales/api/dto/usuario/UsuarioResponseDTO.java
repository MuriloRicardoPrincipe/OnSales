package onsales.api.dto.usuario;

import onsales.api.model.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nome,
        String email,
        String senha,
        String role,
        LocalDateTime criacao
) {
    public UsuarioResponseDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getRole(),
                usuario.getCriacao());
    }
}
