package com.zup.lucasciscar.cartaoproposta.dto.response;

public class CarteiraClientResponse {

    private Resultado resultado;
    private String id;

    public enum Resultado {
        ASSOCIADA, FALHA;
    }

    public CarteiraClientResponse(Resultado resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public Resultado getResultado() {
        return resultado;
    }
}
