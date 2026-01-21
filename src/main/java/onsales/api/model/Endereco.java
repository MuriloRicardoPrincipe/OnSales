package onsales.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "endereco")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @NotBlank
    private String rua;
    //em Bauru e padrão marcar o numero da quadra/bloco ex 9-20
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String estado;
    @NotBlank
    private String cep;

    public Endereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep, Cliente cliente) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.cliente = cliente;
    }
    public void AtualizacaoEndereco(String rua, String numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;

    }
}
