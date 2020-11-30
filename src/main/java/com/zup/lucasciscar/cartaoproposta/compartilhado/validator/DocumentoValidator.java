package com.zup.lucasciscar.cartaoproposta.compartilhado.validator;

import com.zup.lucasciscar.cartaoproposta.proposta.PropostaRepository;
import com.zup.lucasciscar.cartaoproposta.proposta.PropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DocumentoValidator implements Validator {

    @Autowired
    private PropostaRepository propostaRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return PropostaRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors())
            return;

        PropostaRequest request = (PropostaRequest) o;
        if(!request.documentoValido())
            errors.rejectValue("documento", null, "CPF/CNPJ inválido");
        if(propostaRepository.findByDocumento(request.getDocumento()).isPresent())
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "CPF/CNPJ já cadastrado");
    }
}
