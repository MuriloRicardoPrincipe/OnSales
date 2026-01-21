package onsales.api.dto.venda;
import jakarta.validation.constraints.NotNull;
import onsales.api.dto.vendaItem.VendaItemCreateDTO;
import onsales.api.model.VendaItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record VendaCreateDTO (
        @NotNull(message = "Informe o cliente!")
        UUID clienteId
        ){
}
