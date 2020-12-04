package com.zup.lucasciscar.cartaoproposta.controller;

import com.zup.lucasciscar.cartaoproposta.dto.request.BiometriaRequest;
import com.zup.lucasciscar.cartaoproposta.model.Biometria;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.repository.BiometriaRepository;
import com.zup.lucasciscar.cartaoproposta.repository.CartaoRepository;
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
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BiometriaRepository biometriaRepository;

    @PostMapping("/cartoes/{idCartao}/biometrias")
    @Transactional
    public ResponseEntity<?> criarBiometria(@RequestBody @Valid BiometriaRequest biometriaRequest,
                                            @PathVariable("idCartao") UUID idCartao, UriComponentsBuilder builder) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);
        Cartao cartao = cartaoOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        Biometria biometria = biometriaRequest.toModel(cartao);
        biometriaRepository.save(biometria);

        URI location = builder.path("/{idCartao}/biometrias/{id}").build(idCartao, biometria.getId());
        return ResponseEntity.created(location).build();
    }
}
