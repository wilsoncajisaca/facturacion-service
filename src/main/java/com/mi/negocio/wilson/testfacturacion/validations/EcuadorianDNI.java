package com.mi.negocio.wilson.testfacturacion.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DNIEcuadorianValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EcuadorianDNI {
    String message() default "Identificacion ecuatoriana inválida";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
