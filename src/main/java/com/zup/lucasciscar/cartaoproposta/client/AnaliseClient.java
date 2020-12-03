package com.zup.lucasciscar.cartaoproposta.client;

import com.zup.lucasciscar.cartaoproposta.dto.request.PropostaAnaliseRequest;
import com.zup.lucasciscar.cartaoproposta.dto.response.PropostaAnaliseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "analise", url = "${analise-client.url}")
public interface AnaliseClient {

    @PostMapping("/api/solicitacao")
    PropostaAnaliseResponse solicitarAnalise(@RequestBody PropostaAnaliseRequest propostaAnalise);
}
