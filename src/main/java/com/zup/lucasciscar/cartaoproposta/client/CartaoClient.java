package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.response.CartaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(value = "cartao", url = "${cartao-client.url}")
public interface CartaoClient {

    @GetMapping("/api/cartoes?idProposta={id}")
    CartaoClientResponse associarCartao(@PathVariable("id") UUID id);
}

