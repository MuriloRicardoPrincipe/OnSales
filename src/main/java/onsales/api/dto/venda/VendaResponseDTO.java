package onsales.api.dto.venda;

import onsales.api.model.Venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record VendaResponseDTO(
        UUID id,
        UUID clienteId,
        BigDecimal total,
        LocalDateTime criacao,
        LocalDateTime atualizacao
) {
    public VendaResponseDTO(Venda venda){
        this(
                venda.getId(),
                venda.getCliente().getId(),
                venda.getTotal(),
                venda.getCriacao(),
                venda.getAtualizado()
        );
    }
}
