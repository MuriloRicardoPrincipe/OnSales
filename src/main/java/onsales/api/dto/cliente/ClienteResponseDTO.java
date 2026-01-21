package onsales.api.dto.cliente;

import onsales.api.model.Cliente;
import onsales.api.model.TipoDocumento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ClienteResponseDTO(UUID id,
                                 String nome,
                                 TipoDocumento tipoDocumento,
                                 String documento,
                                 LocalDateTime criacao ) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getTipoDocumento(), cliente.getDocumento(), cliente.getCriacao());
    }
}