package com.zup.lucasciscar.cartaoproposta.proposta;

import com.zup.lucasciscar.cartaoproposta.compartilhado.ExecutorTransacao;
import com.zup.lucasciscar.cartaoproposta.compartilhado.client.AnaliseClient;
import com.zup.lucasciscar.cartaoproposta.compartilhado.validator.DocumentoValidator;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

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
        URI location = builder.path("/propostas/{id}").build(proposta.getId());

        PropostaAnaliseRequest propostaAnalise = new PropostaAnaliseRequest(proposta.getDocumento(),
                proposta.getNome(), proposta.getId());

        try {
            Map<String, Object> resultadoAnalise = analiseClient.solicitarAnalise(propostaAnalise);
            if(resultadoAnalise.get("resultadoSolicitacao").equals("SEM_RESTRICAO"))
                proposta.setStatus(Proposta.Status.ELEGIVEL);
        } catch(FeignException ex) {
            proposta.setStatus(Proposta.Status.NAO_ELEGIVEL);
        } finally {
            executorTransacao.atualizar(proposta);
        }

        return ResponseEntity.created(location).build();
    }

}
