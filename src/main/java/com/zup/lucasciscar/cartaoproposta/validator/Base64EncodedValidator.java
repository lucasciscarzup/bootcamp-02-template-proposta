package com.zup.lucasciscar.cartaoproposta.validator;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class Base64EncodedValidator implements ConstraintValidator<Base64Encoded, String> {

    @Override
    public void initialize(Base64Encoded constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Base64.isBase64(value);
    }
}
