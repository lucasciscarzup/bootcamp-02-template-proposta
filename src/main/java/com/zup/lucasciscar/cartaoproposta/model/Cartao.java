package com.zup.lucasciscar.cartaoproposta.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String numero;
    @NotNull
    @Positive
    private BigDecimal limite;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DESBLOQUEADO;
    @NotNull
    @Valid
    @OneToOne
    private Proposta proposta;

    public enum Status {
        BLOQUEADO, DESBLOQUEADO;
    }

    @Deprecated
    public Cartao() {}

    public Cartao(@NotBlank String numero, @NotNull @Positive BigDecimal limite, @NotNull @Valid Proposta proposta) {
        this.numero = numero;
        this.limite = limite;
        this.proposta = proposta;
    }

    public UUID getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
