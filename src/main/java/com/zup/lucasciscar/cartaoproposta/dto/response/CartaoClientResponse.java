package com.zup.lucasciscar.cartaoproposta.dto.response;

import java.math.BigDecimal;

public class CartaoClientResponse {

    private String id;
    private BigDecimal limite;
    private Long idProposta;

    public CartaoClientResponse() {}

    public CartaoClientResponse(String id, BigDecimal limite, Long idProposta) {
        this.id = id;
        this.limite = limite;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}
