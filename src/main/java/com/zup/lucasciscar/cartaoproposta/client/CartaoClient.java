package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.response.CartaoClientResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartao", url = "${cartao-client.url}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes?idProposta={idProposta}")
    CartaoClientResponse associarCartao(@PathVariable("idProposta") Long idProposta);
}

