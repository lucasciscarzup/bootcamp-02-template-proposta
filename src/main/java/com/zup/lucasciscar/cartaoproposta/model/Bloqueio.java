package com.zup.lucasciscar.cartaoproposta.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bloqueios")
public class Bloqueio {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;
    @NotNull
    @Valid
    @OneToOne
    private Cartao cartao;
    @NotNull
    private LocalDateTime bloqueadoEm = LocalDateTime.now();

    @Deprecated
    public Bloqueio() {}

    public Bloqueio(@NotBlank String ipCliente, @NotBlank String userAgent, @NotNull @Valid Cartao cartao) {
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
