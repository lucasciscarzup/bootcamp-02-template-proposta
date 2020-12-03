package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.request.PropostaAnaliseRequest;
import com.zup.lucasciscar.cartaoproposta.dto.response.PropostaAnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "analise", url = "${analise-client.url}")
public interface AnaliseClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    PropostaAnaliseResponse solicitarAnalise(@RequestBody PropostaAnaliseRequest propostaAnalise);
}
