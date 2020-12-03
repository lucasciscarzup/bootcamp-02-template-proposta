package com.zup.lucasciscar.cartaoproposta.dto.request;

public class PropostaAnaliseRequest {

    private String documento;
    private String nome;
    private Long idProposta;

    public PropostaAnaliseRequest(String documento, String nome, Long idProposta) {
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

    public Long getIdProposta() {
        return idProposta;
    }
}
