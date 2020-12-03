package com.zup.lucasciscar.cartaoproposta.dto.response;

public class PropostaAnaliseResponse {

    private String documento;
    private String nome;
    private Restricao resultadoSolicitacao;
    private Long idProposta;

    public enum Restricao {
        COM_RESTRICAO, SEM_RESTRICAO;
    }

    public PropostaAnaliseResponse() {}

    public PropostaAnaliseResponse(String documento, String nome, Restricao resultadoSolicitacao, Long idProposta) {
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

    public Long getIdProposta() {
        return idProposta;
    }
}
