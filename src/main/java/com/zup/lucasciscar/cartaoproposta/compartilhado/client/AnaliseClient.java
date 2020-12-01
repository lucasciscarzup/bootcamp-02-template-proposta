package com.zup.lucasciscar.cartaoproposta.compartilhado.client;

import com.zup.lucasciscar.cartaoproposta.proposta.PropostaAnaliseRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "analise", url = "${analise-client.url}")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    Map<String, Object> solicitarAnalise(@RequestBody PropostaAnaliseRequest propostaAnalise);
}
