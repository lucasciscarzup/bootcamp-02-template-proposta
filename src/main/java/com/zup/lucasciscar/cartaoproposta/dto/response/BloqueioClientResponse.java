package com.zup.lucasciscar.cartaoproposta.dto.response;

public class BloqueioClientResponse {

    private Resultado resultado;

    public enum Resultado {
        BLOQUEADO, FALHA;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public Resultado getResultado() {
        return resultado;
    }
}
