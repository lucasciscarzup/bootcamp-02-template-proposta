package com.zup.lucasciscar.cartaoproposta.proposta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zup.lucasciscar.cartaoproposta.compartilhado.ExecutorTransacao;
import com.zup.lucasciscar.cartaoproposta.compartilhado.client.AnaliseClient;
import com.zup.lucasciscar.cartaoproposta.compartilhado.validator.DocumentoValidator;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class PropostaController {

    @Autowired
    private ExecutorTransacao executorTransacao;
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
    public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest propostaRequest,
                                           UriComponentsBuilder builder) {
        if(propostaRepository.findByDocumento(propostaRequest.getDocumento()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF/CNPJ já cadastrado");

        Proposta proposta = propostaRequest.toModel();
        executorTransacao.salvar(proposta);

        PropostaLegadoRequest propostaAnalise = new PropostaLegadoRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId());

        Map<String, Object> resultadoAnalise;
        try {
            resultadoAnalise = analiseClient.solicitarAnalise(propostaAnalise);
            if(resultadoAnalise.get("resultadoSolicitacao").equals("SEM_RESTRICAO"))
                proposta.setStatus(Proposta.Status.ELEGIVEL);
        } catch(FeignException.FeignClientException.UnprocessableEntity ex) {
            try {
                resultadoAnalise = new ObjectMapper().readValue(ex.contentUTF8(), Map.class);
                if(resultadoAnalise.get("resultadoSolicitacao").equals("COM_RESTRICAO"))
                    proposta.setStatus(Proposta.Status.NAO_ELEGIVEL);
            } catch (JsonProcessingException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Servidor indisponível");
            }
        } finally {
            executorTransacao.atualizar(proposta);
        }

        URI location = builder.path("/propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/propostas/{id}")
    public ResponseEntity<?> buscarProposta(@PathVariable("id") Long idProposta) {
        Optional<Proposta> propostaOpt = propostaRepository.findById(idProposta);
        if(propostaOpt.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proposta não encontrada");

        Proposta proposta = propostaOpt.get();
        PropostaResponse propostaResponse = new PropostaResponse(proposta);

        return ResponseEntity.ok(propostaResponse);
    }

}
