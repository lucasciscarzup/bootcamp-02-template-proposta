package com.zup.lucasciscar.cartaoproposta.dto.request;

import com.zup.lucasciscar.cartaoproposta.model.Carteira;

public class CarteiraClientRequest {

    private String email;
    private Carteira.Tipo carteira;

    public CarteiraClientRequest(String email, Carteira.Tipo carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public Carteira.Tipo getCarteira() {
        return carteira;
    }
}
