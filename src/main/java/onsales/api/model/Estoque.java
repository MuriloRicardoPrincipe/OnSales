package onsales.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estoque")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    @Column(name = "criacao", nullable = false, updatable = false)
    private LocalDateTime criacao;

    @Column(name = "atualizado", updatable = false)
    private LocalDateTime atualizado;

    public Estoque( Produto produto, Integer quantidade,  LocalDateTime criacao) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.criacao = criacao;
    }

    public void AtualizacaoEstoque(  Produto produto, Integer quantidade, LocalDateTime atualizado) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.atualizado = atualizado;
    }

    public void RetiradaEstoque(Integer quantidadeEstoque, Integer quantidadeSolicitado){
        this.quantidade = quantidadeEstoque - quantidadeSolicitado;
    }

    public void DevolvendoEstoque(Integer quantidadeEstoque, Integer quantidadeDevolvida){
        this.quantidade = quantidadeEstoque + quantidadeDevolvida;
    }

}
