package onsales.api.dto.produto;


import onsales.api.model.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        BigDecimal preco,
        Boolean ativo,
        LocalDateTime criacao,
        LocalDateTime atualizado
) {
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getAtivo(),
                produto.getCriacao(),
                produto.getAtualizado()
        );
    }
}
