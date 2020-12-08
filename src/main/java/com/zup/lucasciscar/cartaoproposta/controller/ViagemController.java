package com.zup.lucasciscar.cartaoproposta.controller;

import com.zup.lucasciscar.cartaoproposta.dto.request.ViagemRequest;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.model.Viagem;
import com.zup.lucasciscar.cartaoproposta.repository.CartaoRepository;
import com.zup.lucasciscar.cartaoproposta.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ViagemRepository viagemRepository;

    @PostMapping("/cartoes/{idCartao}/viagens")
    @Transactional
    public ResponseEntity<?> criarAvisoViagem(@PathVariable("idCartao") UUID idCartao,
                                              @RequestBody @Valid ViagemRequest viagemRequest, HttpServletRequest request) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);
        Cartao cartao = cartaoOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        Viagem viagem = viagemRequest.toModel(request);
        viagemRepository.save(viagem);

        return ResponseEntity.ok().build();
    }
}
