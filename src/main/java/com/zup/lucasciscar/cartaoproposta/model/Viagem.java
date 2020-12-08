package com.zup.lucasciscar.cartaoproposta.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "viagens")
public class Viagem {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String destino;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;
    @NotNull
    @PastOrPresent
    private LocalDateTime criadoEm = LocalDateTime.now();
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;
    @NotNull
    @Valid
    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Viagem() {}

    public Viagem(@NotBlank String destino, @NotNull @Future LocalDate dataTermino, @NotBlank String ipCliente,
                  @NotBlank String userAgent, @NotNull @Valid Cartao cartao) {
        this.destino = destino;
        this.dataTermino = dataTermino;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }
}
