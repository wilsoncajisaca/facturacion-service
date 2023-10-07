package com.mi.negocio.wilson.testfacturacion.exceptions;

import com.mi.negocio.wilson.testfacturacion.pojos.errors.ApiFieldError;

/**
 * Controlar las excepciones de request
 */
public class RequestValidationException extends Exception {
    private static final long serialVersionUID = 1L;
    private ApiFieldError fieldError;

    public RequestValidationException(ApiFieldError listError){
        this.fieldError = listError;
    }

    public ApiFieldError getApiListError(){
        return this.fieldError;
    }
}
