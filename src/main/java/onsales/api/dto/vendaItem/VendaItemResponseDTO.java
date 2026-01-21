package onsales.api.dto.vendaItem;

import onsales.api.model.VendaItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record VendaItemResponseDTO (
        UUID id,
        UUID venda_id,
        UUID produto_id,
        Integer quantidade,
        BigDecimal total,
        LocalDateTime criado,
        LocalDateTime atualizacao
){
    public VendaItemResponseDTO(VendaItem vendaItem){
        this(
                vendaItem.getId(),
                vendaItem.getVenda().getId(),
                vendaItem.getProduto().getId(),
                vendaItem.getQuantidade(),
                vendaItem.getTotal(),
                vendaItem.getCriacao(),
                vendaItem.getAtualizado()
                );
    }
}
