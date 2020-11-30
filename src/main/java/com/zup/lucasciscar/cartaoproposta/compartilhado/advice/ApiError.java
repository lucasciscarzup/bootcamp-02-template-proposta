package com.zup.lucasciscar.cartaoproposta.compartilhado.advice;

import java.util.Arrays;
import java.util.List;

public class ApiError {

    private List<String> errors;

    public ApiError(List<String> errors) {
        this.errors = errors;
    }

    public ApiError(String error) {
        this.errors = Arrays.asList(error);
    }

    public List<String> getErrors() {
        return errors;
    }
}
