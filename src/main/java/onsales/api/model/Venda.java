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
@Table(name = "venda")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VendaItem> vendaItens = new ArrayList<>();

    @Column( precision = 15, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "criacao", nullable = false, updatable = false)
    private LocalDateTime criacao;

    @Column(name = "atualizado", nullable = false)
    private LocalDateTime atualizado;

    public Venda(Cliente cliente, BigDecimal total, LocalDateTime criacao, LocalDateTime atualizado) {
        this.cliente = cliente;
        this.total = total;
        this.criacao = criacao;
        this.atualizado = atualizado;
    }

    public void AtualizacaoVenda(Cliente cliente, LocalDateTime atualizado) {
        this.cliente = cliente;
        this.atualizado = atualizado;
    }

    public void AtualizaVendaTotal(BigDecimal total){
        this.total = total;
    }

}
