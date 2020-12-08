package com.zup.lucasciscar.cartaoproposta.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.lucasciscar.cartaoproposta.model.Viagem;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class ViagemRequest {

    @NotBlank
    private String destino;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataTermino;

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Viagem toModel(HttpServletRequest request) {
        return new Viagem(destino, dataTermino, request.getRemoteAddr(), request.getHeader("User-Agent"));
    }
}
