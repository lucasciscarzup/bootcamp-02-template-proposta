package com.zup.lucasciscar.cartaoproposta.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public class CartaoClientResponse {

    private String id;
    private BigDecimal limite;
    private UUID idProposta;

    public CartaoClientResponse() {}

    public CartaoClientResponse(String id, BigDecimal limite, UUID idProposta) {
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

    public UUID getIdProposta() {
        return idProposta;
    }
}
