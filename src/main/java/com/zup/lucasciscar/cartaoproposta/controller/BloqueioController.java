package com.zup.lucasciscar.cartaoproposta.controller;

import com.zup.lucasciscar.cartaoproposta.model.Bloqueio;
import com.zup.lucasciscar.cartaoproposta.model.Cartao;
import com.zup.lucasciscar.cartaoproposta.repository.BloqueioRepository;
import com.zup.lucasciscar.cartaoproposta.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RestController
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private BloqueioRepository bloqueioRepository;

    @PostMapping("/cartoes/{idCartao}/bloqueio")
    @Transactional
    public ResponseEntity<?> bloquearCartao(@PathVariable("idCartao") UUID idCartao, HttpServletRequest request) {
        Optional<Cartao> cartaoOpt = cartaoRepository.findById(idCartao);
        Cartao cartao = cartaoOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Cartão não encontrado"));

        if(bloqueioRepository.findByCartao(cartao).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cartão já bloqueado");

        Bloqueio bloqueio = new Bloqueio(request.getRemoteAddr(), request.getHeader("User-Agent"), cartao);
        bloqueioRepository.save(bloqueio);

        return ResponseEntity.ok().build();
    }
}
