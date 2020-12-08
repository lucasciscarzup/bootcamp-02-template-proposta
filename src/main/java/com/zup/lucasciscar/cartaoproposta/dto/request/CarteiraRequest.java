package com.zup.lucasciscar.cartaoproposta.dto.request;

import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.model.Carteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;
    @NotNull
    private Carteira.Tipo tipo;

    public CarteiraRequest(@NotBlank @Email String email, @NotNull Carteira.Tipo tipo) {
        this.email = email;
        this.tipo = tipo;
    }

    public Carteira toModel(Cartao cartao) {
        return new Carteira(email, tipo, cartao);
    }

    public String getEmail() {
        return email;
    }

    public Carteira.Tipo getTipo() {
        return tipo;
    }
}
