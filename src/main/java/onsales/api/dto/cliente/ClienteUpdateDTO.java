package onsales.api.dto.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import onsales.api.model.TipoDocumento;
import onsales.api.validation.DocumentoValido;

import java.util.UUID;

public record ClienteUpdateDTO(
        @NotBlank(message = "Nome Empresa é obrigatório")
        String nome,
        @NotNull(message = "Tipo de Documento é obrigatório, envie CNPJ ou CPF!")
        TipoDocumento tipoDocumento,
        @NotBlank(message = "Documento é obrigatório!")
        @DocumentoValido
        String documento) {
}
