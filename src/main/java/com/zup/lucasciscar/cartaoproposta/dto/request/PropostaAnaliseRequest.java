package com.zup.lucasciscar.cartaoproposta.dto.request;

import java.util.UUID;

public class PropostaAnaliseRequest {

    private String documento;
    private String nome;
    private UUID idProposta;

    public PropostaAnaliseRequest(String documento, String nome, UUID idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public UUID getIdProposta() {
        return idProposta;
    }
}
