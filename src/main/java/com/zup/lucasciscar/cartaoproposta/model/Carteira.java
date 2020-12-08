package com.zup.lucasciscar.cartaoproposta.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    @Email
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    @NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

    public enum Tipo {
        PAYPAL, SAMSUNG_PAY;
    }

    @Deprecated
    public Carteira() {}

    public Carteira(@NotBlank @Email String email, @NotNull Tipo tipo, @NotNull @Valid Cartao cartao) {
        this.email = email;
        this.tipo = tipo;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }
}
