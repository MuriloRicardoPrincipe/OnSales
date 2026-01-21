package onsales.api.dto.estoque;

import onsales.api.model.Estoque;

import java.time.LocalDateTime;
import java.util.UUID;

public record EstoqueResponseDTO(
        UUID id,
        UUID produtoId,
        Integer quantidade,
        LocalDateTime criacao,
        LocalDateTime atualizado
) {
    public EstoqueResponseDTO(Estoque estoque){
        this(
                estoque.getId(),
                estoque.getProduto().getId(),
                estoque.getQuantidade(),
                estoque.getCriacao(),
                estoque.getAtualizado()
                );
    }
}
