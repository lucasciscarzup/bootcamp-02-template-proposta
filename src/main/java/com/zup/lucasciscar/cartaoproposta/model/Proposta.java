package com.zup.lucasciscar.cartaoproposta.model;

import com.zup.lucasciscar.cartaoproposta.converter.StringAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "propostas")
public class Proposta {

    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    @Convert(converter = StringAttributeConverter.class)
    @Column(unique = true)
    private String documento;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotNull
    @Positive
    private BigDecimal salario;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String numeroCartao;

    public enum Status {
        ELEGIVEL, NAO_ELEGIVEL
    }

    @Deprecated
    public Proposta() {}

    public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
                    @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
    }

    public UUID getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public Status getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }
}
