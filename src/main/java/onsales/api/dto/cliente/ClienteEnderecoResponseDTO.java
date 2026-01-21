package onsales.api.dto.cliente;

import onsales.api.dto.endereco.EnderecoResponseDTO;
import onsales.api.model.Cliente;
import onsales.api.model.TipoDocumento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ClienteEnderecoResponseDTO(UUID id,
                                         String nome,
                                         TipoDocumento tipoDocumento,
                                         String documento,
                                         LocalDateTime criacao,
                                         List<EnderecoResponseDTO> endereco) {
    public ClienteEnderecoResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTipoDocumento(), cliente.getDocumento(), cliente.getCriacao(),
                cliente.getEnderecos()
                        .stream()
                        .map(EnderecoResponseDTO::new)
                        .toList());
    }
}