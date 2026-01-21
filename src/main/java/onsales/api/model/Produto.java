package onsales.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    @Column(precision = 15, scale = 2, nullable = false)
    private BigDecimal preco;

    private Boolean ativo;

    @Column(name = "criacao", nullable = false, updatable = false)
    private LocalDateTime criacao;

    @Column(name = "atualizado", nullable = false, updatable = false)
    private LocalDateTime atualizado;

    public Produto(LocalDateTime atualizado, LocalDateTime criacao, Boolean ativo, BigDecimal preco, String nome, String descricao) {
        this.atualizado = atualizado;
        this.criacao = criacao;
        this.ativo = ativo;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }

    public void AtualizacaoProduto(LocalDateTime atualizado, BigDecimal preco, String nome, String descricao) {
        this.atualizado = atualizado;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
    }
    public void DesativarProduto(Boolean ativo){
        this.ativo = ativo;
    }
}
