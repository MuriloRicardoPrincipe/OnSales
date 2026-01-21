package onsales.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "contato")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @NotBlank
    private String telefone;
    @NotBlank
    private String email;
    @NotBlank
    private String celular;

    public Contato(Cliente cliente, String telefone, String email, String celular) {
        this.cliente = cliente;
        this.telefone = telefone;
        this.email = email;
        this.celular = celular;
    }

    public void atualizarContato(String telefone, String email, String celular){
        this.telefone = telefone;
        this.email = email;
        this.celular = celular;
    }
}