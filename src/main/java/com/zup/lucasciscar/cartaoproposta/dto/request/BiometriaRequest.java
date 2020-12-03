package com.zup.lucasciscar.cartaoproposta.dto.request;

import com.zup.lucasciscar.cartaoproposta.model.Biometria;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.validator.Base64Encoded;

import javax.validation.constraints.NotBlank;
import java.util.Base64;

public class BiometriaRequest {

    @NotBlank
    @Base64Encoded
    private String digital;

    public void setDigital(String digital) {
        this.digital = digital;
    }

    public Biometria toModel(Cartao cartao) {
        return new Biometria(Base64.getDecoder().decode(digital), cartao);
    }
}
