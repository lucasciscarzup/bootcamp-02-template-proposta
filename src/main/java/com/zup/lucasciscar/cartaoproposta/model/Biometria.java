package com.zup.lucasciscar.cartaoproposta.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name = "biometrias")
public class Biometria {

    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    @Lob
    private byte[] digital;
    @NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {}

    public Biometria(@NotNull byte[] digital, @NotNull @Valid Cartao cartao) {
        this.digital = digital;
        this.cartao = cartao;
    }

    public UUID getId() {
        return id;
    }
}
