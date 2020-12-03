package com.zup.lucasciscar.cartaoproposta.dto.response;

import com.zup.lucasciscar.cartaoproposta.model.Proposta;

import java.math.BigDecimal;

public class PropostaResponse {

    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private Proposta.Status status;
    private String numeroCartao;

    public PropostaResponse(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.numeroCartao = proposta.getNumeroCartao();
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

    public Proposta.Status getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
