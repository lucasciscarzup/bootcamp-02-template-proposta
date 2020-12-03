package com.zup.lucasciscar.cartaoproposta.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String numero;
    @NotNull
    @Positive
    private BigDecimal limite;
    @NotNull
    @Valid
    @OneToOne
    private Proposta proposta;

    public Cartao(@NotBlank String numero, @NotNull @Positive BigDecimal limite, @NotNull @Valid Proposta proposta) {
        this.numero = numero;
        this.limite = limite;
        this.proposta = proposta;
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
