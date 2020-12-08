package com.zup.lucasciscar.cartaoproposta.dto.response;

public class ViagemClientResponse {

    private Resultado resultado;

    public enum Resultado {
        CRIADO, FALHA;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Resultado getResultado() {
        return resultado;
    }
}
