package com.zup.lucasciscar.cartaoproposta.dto.request;

import java.time.LocalDate;

public class ViagemClientRequest {

    private String destino;
    private LocalDate validoAte;

    public ViagemClientRequest(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
