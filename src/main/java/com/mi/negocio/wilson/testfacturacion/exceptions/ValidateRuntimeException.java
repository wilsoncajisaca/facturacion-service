package com.mi.negocio.wilson.testfacturacion.exceptions;

import com.mi.negocio.wilson.testfacturacion.pojos.errors.ApiFieldError;

/**
 * Controla el resto de exceptions
 */
public class ValidateRuntimeException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private ApiFieldError fieldError;

    public ValidateRuntimeException(ApiFieldError listError){
        this.fieldError = listError;
    }

    public ApiFieldError getApiListError(){
        return this.fieldError;
    }
}
