package com.zup.lucasciscar.cartaoproposta.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.lucasciscar.cartaoproposta.client.AnaliseClient;
import com.zup.lucasciscar.cartaoproposta.dto.request.PropostaAnaliseRequest;
import com.zup.lucasciscar.cartaoproposta.dto.request.PropostaRequest;
import com.zup.lucasciscar.cartaoproposta.dto.response.PropostaAnaliseResponse;
import com.zup.lucasciscar.cartaoproposta.dto.response.PropostaResponse;
import com.zup.lucasciscar.cartaoproposta.model.Proposta;
import com.zup.lucasciscar.cartaoproposta.repository.PropostaRepository;
import com.zup.lucasciscar.cartaoproposta.validator.DocumentoValidator;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class PropostaController {

    @Autowired
    private DocumentoValidator documentoValidator;
    @Autowired
    private PropostaRepository propostaRepository;
    @Autowired
    private AnaliseClient analiseClient;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(documentoValidator);
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest propostaRequest,
                                           UriComponentsBuilder builder) {
        if(propostaRepository.findByDocumento(propostaRequest.getDocumento()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF/CNPJ já cadastrado");

        Proposta proposta = propostaRequest.toModel();
        propostaRepository.save(proposta);

        PropostaAnaliseRequest propostaAnaliseRequest = new PropostaAnaliseRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId());

        PropostaAnaliseResponse propostaAnaliseResponse;
        try {
            propostaAnaliseResponse = analiseClient.solicitarAnalise(propostaAnaliseRequest);
            if(propostaAnaliseResponse.getResultadoSolicitacao().equals(PropostaAnaliseResponse.Restricao.SEM_RESTRICAO))
                proposta.setStatus(Proposta.Status.ELEGIVEL);
        } catch(FeignException.FeignClientException.UnprocessableEntity ex) {
            try {
                propostaAnaliseResponse = new ObjectMapper().readValue(ex.contentUTF8(), PropostaAnaliseResponse.class);
                if(propostaAnaliseResponse.getResultadoSolicitacao().equals(PropostaAnaliseResponse.Restricao.COM_RESTRICAO))
                    proposta.setStatus(Proposta.Status.NAO_ELEGIVEL);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Servidor indisponível");
            }
        } finally {
            propostaRepository.save(proposta);
        }

        URI location = builder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> buscarProposta(@PathVariable("id") Long idProposta) {
        Optional<Proposta> propostaOpt = propostaRepository.findById(idProposta);
        Proposta proposta = propostaOpt.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada"));

        PropostaResponse propostaResponse = new PropostaResponse(proposta);
        return ResponseEntity.ok(propostaResponse);
    }

}
