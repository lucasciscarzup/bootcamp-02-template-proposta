package com.zup.lucasciscar.cartaoproposta.dto.response;

import java.util.UUID;

public class PropostaAnaliseResponse {

    private String documento;
    private String nome;
    private Restricao resultadoSolicitacao;
    private UUID idProposta;

    public enum Restricao {
        COM_RESTRICAO, SEM_RESTRICAO;
    }

    public PropostaAnaliseResponse() {}

    public PropostaAnaliseResponse(String documento, String nome, Restricao resultadoSolicitacao, UUID idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Restricao getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public UUID getIdProposta() {
        return idProposta;
    }
}
