package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.response.CartaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
import java.util.UUID;

@FeignClient(value = "cartao", url = "${cartao-client.url}")
public interface CartaoClient {

    @GetMapping("/api/cartoes?idProposta={id}")
    CartaoClientResponse associarCartao(@PathVariable("id") UUID id);

    @PostMapping("/api/cartoes/{numCartao}/bloqueios")
    String bloquearCartao(@PathVariable("numCartao") String numCartao, @RequestBody Map<String, Object> body);
}

