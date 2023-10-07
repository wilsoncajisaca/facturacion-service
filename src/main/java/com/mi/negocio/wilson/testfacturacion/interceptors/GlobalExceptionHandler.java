package com.mi.negocio.wilson.testfacturacion.interceptors;

import com.mi.negocio.wilson.testfacturacion.exceptions.RequestValidationException;
import com.mi.negocio.wilson.testfacturacion.exceptions.ValidateRuntimeException;
import com.mi.negocio.wilson.testfacturacion.pojos.errors.ApiFieldError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Filter of exception into request
 *
 * @author wajisaca
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * @param requestValidationException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<Object> handleRequestValidationException(
            RequestValidationException requestValidationException, WebRequest webRequest){
        log.debug("Exception for return ApiFieldError: {}", requestValidationException);
        ApiFieldError listError = requestValidationException.getApiListError();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listError);
    }

    /**
     * @param validateRuntimeException Throw Error
     * @param webRequest   Request
     * @return Final custom exception
     */
    @ExceptionHandler(ValidateRuntimeException.class)
    public ResponseEntity<Object> handleGeneralException(ValidateRuntimeException validateRuntimeException, WebRequest webRequest){
        log.debug("Exception for return handleGeneralException: {}", validateRuntimeException);
        ApiFieldError error = validateRuntimeException.getApiListError();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
