package com.zup.lucasciscar.cartaoproposta.dto.request;

import com.zup.lucasciscar.cartaoproposta.model.Bloqueio;

public class BloqueioClientRequest {

    private String sistemaResponsavel;

    public BloqueioClientRequest(Bloqueio bloqueio) {
        this.sistemaResponsavel = bloqueio.getUserAgent();
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
