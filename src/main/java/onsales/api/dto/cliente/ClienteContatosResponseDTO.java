package onsales.api.dto.cliente;

import onsales.api.dto.contato.ContatoResponseDTO;
import onsales.api.model.Cliente;
import onsales.api.model.TipoDocumento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ClienteContatosResponseDTO(UUID id,
                                         String nome,
                                         TipoDocumento tipoDocumento,
                                         String documento,
                                         LocalDateTime criacao,
                                         List<ContatoResponseDTO> contatos) {
    public ClienteContatosResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTipoDocumento(), cliente.getDocumento(), cliente.getCriacao(),
                cliente.getContatos()
                        .stream()
                        .map(ContatoResponseDTO::new)
                        .toList());
    }
}
