package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.request.BloqueioClientRequest;
import com.zup.lucasciscar.cartaoproposta.dto.request.CarteiraClientRequest;
import com.zup.lucasciscar.cartaoproposta.dto.request.ViagemClientRequest;
import com.zup.lucasciscar.cartaoproposta.dto.response.BloqueioClientResponse;
import com.zup.lucasciscar.cartaoproposta.dto.response.CartaoClientResponse;
import com.zup.lucasciscar.cartaoproposta.dto.response.CarteiraClientResponse;
import com.zup.lucasciscar.cartaoproposta.dto.response.ViagemClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(value = "cartao", url = "${cartao-client.url}")
public interface CartaoClient {

    @GetMapping("/api/cartoes?idProposta={id}")
    CartaoClientResponse associarCartao(@PathVariable("id") UUID id);

    @PostMapping("/api/cartoes/{numCartao}/bloqueios")
    BloqueioClientResponse bloquearCartao(@PathVariable("numCartao") String numCartao,
                                          @RequestBody BloqueioClientRequest bloqueio);

    @PostMapping("/api/cartoes/{numCartao}/avisos")
    ViagemClientResponse criarAvisoViagemCartao(@PathVariable("numCartao") String numCartao,
                                                ViagemClientRequest avisoViagem);

    @PostMapping("/api/cartoes/{numCartao}/carteiras")
    CarteiraClientResponse criarCarteiraCartao(@PathVariable("numCartao") String numCartao,
                                               CarteiraClientRequest carteira);
}

