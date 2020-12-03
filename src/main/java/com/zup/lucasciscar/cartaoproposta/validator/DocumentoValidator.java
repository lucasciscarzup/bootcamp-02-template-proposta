package com.zup.lucasciscar.cartaoproposta.validator;

import com.zup.lucasciscar.cartaoproposta.repository.PropostaRepository;
import com.zup.lucasciscar.cartaoproposta.dto.request.PropostaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
    }
}
