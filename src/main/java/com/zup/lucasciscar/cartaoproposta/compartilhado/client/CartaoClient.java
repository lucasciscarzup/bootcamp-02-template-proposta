package com.zup.lucasciscar.cartaoproposta.compartilhado.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "cartao", url = "${cartao-client.url}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes?idProposta={idProposta}")
    Map<String, Object> associarCartao(@PathVariable("idProposta") Long idProposta);
}

