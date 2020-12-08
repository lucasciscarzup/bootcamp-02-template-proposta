package com.zup.lucasciscar.cartaoproposta.controller;

import com.zup.lucasciscar.cartaoproposta.client.CartaoClient;
import com.zup.lucasciscar.cartaoproposta.dto.request.CarteiraClientRequest;
import com.zup.lucasciscar.cartaoproposta.dto.request.CarteiraRequest;
import com.zup.lucasciscar.cartaoproposta.dto.response.CarteiraClientResponse;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.model.Carteira;
import com.zup.lucasciscar.cartaoproposta.repository.CartaoRepository;
import com.zup.lucasciscar.cartaoproposta.repository.CarteiraRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
public class CarteiraController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CartaoClient cartaoClient;
    @Autowired
    private CarteiraRepository carteiraRepository;

    @PostMapping("/cartoes/{idCartao}/carteiras")
    @Transactional
    public ResponseEntity<?> criarCarteira(@PathVariable("idCartao") UUID idCartao,
                                           @RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder builder) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);
        Cartao cartao = cartaoOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if(carteiraRepository.findByCartaoAndTipo(cartao, carteiraRequest.getTipo()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Carteira " + carteiraRequest.getTipo() + " já associada");

        CarteiraClientRequest carteiraClientRequest = new CarteiraClientRequest(carteiraRequest.getEmail(), carteiraRequest.getTipo());
        Carteira carteira = carteiraRequest.toModel(cartao);
        try {
            CarteiraClientResponse carteiraClientResponse = cartaoClient.criarCarteiraCartao(cartao.getNumero(), carteiraClientRequest);
            if(carteiraClientResponse.getResultado().equals(CarteiraClientResponse.Resultado.ASSOCIADA))
                carteiraRepository.save(carteira);
        } catch(FeignException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Servidor indisponível");
        }

        URI location = builder.path("/cartoes/{idCartao}/carteiras/{id}").build(cartao.getId(), carteira.getId());
        return ResponseEntity.created(location).build();
    }
}
