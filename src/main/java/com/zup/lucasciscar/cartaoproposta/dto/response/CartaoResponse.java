package com.zup.lucasciscar.cartaoproposta.dto.response;

import com.zup.lucasciscar.cartaoproposta.model.Cartao;

import java.math.BigDecimal;

public class CartaoResponse {

    private String numero;
    private BigDecimal limite;

    public CartaoResponse(Cartao cartao) {
        this.numero = cartao.getNumero();
        this.limite = cartao.getLimite();
    }

    public String getNumero() {
        return numero;
    }

    public BigDecimal getLimite() {
        return limite;
    }
}
