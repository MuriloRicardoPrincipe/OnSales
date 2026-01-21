package onsales.api.dto.vendaItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record VendaItemCreateDTO (
        @NotNull(message = "Informe a Venda!")
        UUID vendaId,
        @NotNull(message = "Informe o Produto!")
        UUID produtoId,

        @NotNull(message = "Quantidades maiores que 0!")
        @Positive
        Integer quantidade
){

}
