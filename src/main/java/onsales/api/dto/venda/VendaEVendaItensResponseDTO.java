package onsales.api.dto.venda;

import onsales.api.dto.vendaItem.VendaItemResponseDTO;
import onsales.api.model.Venda;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record VendaEVendaItensResponseDTO(
        UUID id,
        UUID clienteId,
        BigDecimal total,
        LocalDateTime criacao,
        LocalDateTime atualizacao,
        List<VendaItemResponseDTO>vendaItens
) {
    public VendaEVendaItensResponseDTO(Venda venda){
        this(
                venda.getId(),
                venda.getCliente().getId(),
                venda.getTotal(),
                venda.getCriacao(),
                venda.getAtualizado(),
                venda.getVendaItens().stream()
                        .map(VendaItemResponseDTO::new).toList()
        );
    }
}
