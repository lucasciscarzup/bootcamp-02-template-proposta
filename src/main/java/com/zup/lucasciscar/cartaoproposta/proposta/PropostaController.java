package com.zup.lucasciscar.cartaoproposta.proposta;

import com.zup.lucasciscar.cartaoproposta.compartilhado.validator.DocumentoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class PropostaController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DocumentoValidator documentoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(documentoValidator);
    }

    @PostMapping("/propostas")
    @Transactional
    public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest propostaRequest,
                                           UriComponentsBuilder builder) {
        Proposta proposta = propostaRequest.toModel();
        entityManager.persist(proposta);

        URI location = builder.path("/propostas/{id}").build(proposta.getId());

        return ResponseEntity.created(location).build();
    }

}
