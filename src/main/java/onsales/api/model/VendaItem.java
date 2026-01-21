package onsales.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "venda_item")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class VendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venda_id")
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private Integer quantidade;

    @Column(name = "total", nullable = false, updatable = false)
    private BigDecimal total;

    @Column(name = "criacao", nullable = false, updatable = false)
    private LocalDateTime criacao;

    @Column(name = "atualizado", nullable = false)
    private LocalDateTime atualizado;

    public VendaItem(Venda venda, Produto produto,
                     Integer quantidade, BigDecimal total, LocalDateTime criacao, LocalDateTime atualizado) {
        this.venda = venda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = total;
        this.criacao = criacao;
        this.atualizado = atualizado;
    }

    public void AtualizarVendaItem( Produto produto, Integer quantidade, BigDecimal total, LocalDateTime atualizado) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.total = total;
        this.atualizado = atualizado;
    }

}
