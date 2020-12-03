package com.zup.lucasciscar.cartaoproposta.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Base64EncodedValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Base64Encoded {

    String message() default "Digital inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
