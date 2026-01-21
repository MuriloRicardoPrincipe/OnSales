package onsales.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name ="cliente")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false, unique = true, length = 14)
    private String documento;

    @Column(name = "criacao", nullable = false, updatable = false)
    private LocalDateTime criacao;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente(String nome, TipoDocumento tipoDocumento, String documento, LocalDateTime criacao) {
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.criacao = criacao;
    }

    public void atualizarDados(String nome, TipoDocumento tipoDocumento, String documento){
        this.nome = nome;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
    }
}