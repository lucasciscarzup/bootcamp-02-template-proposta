package com.zup.lucasciscar.cartaoproposta.compartilhado.validator;

import com.zup.lucasciscar.cartaoproposta.proposta.PropostaRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DocumentoValidator implements Validator {


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
