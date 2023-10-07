package com.mi.negocio.wilson.testfacturacion.validations;

import com.mi.negocio.wilson.testfacturacion.exceptions.RequestValidationException;
import com.mi.negocio.wilson.testfacturacion.pojos.errors.ApiFieldError;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * General class for validations into services
 *
 * @author wcajisaca
 */
public class Validations {
    public static final String REGEX_MAIL_VALIDATION = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGEX_CELL_VALIDATION = "^09[0-9]{8}$";
    public static final String REGEX_NAME_VALIDATION = "^[a-zA-Z ]+$";

    public static final String REGEX_DNI_VALIDATION = "^[0-9]+$";
    public static void validateFieldRequest(Errors errors) throws RequestValidationException {
        if (errors.hasErrors()) {
            throw new RequestValidationException(new ApiFieldError("-1", errors.getAllErrors()
                    .stream()
                    .map(m -> m.getDefaultMessage())
                    .collect(Collectors.toList())));
        }
    }
}
