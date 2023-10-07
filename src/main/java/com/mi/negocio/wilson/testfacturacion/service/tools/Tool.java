package com.mi.negocio.wilson.testfacturacion.service.tools;

import com.mi.negocio.wilson.testfacturacion.exceptions.ValidateRuntimeException;
import com.mi.negocio.wilson.testfacturacion.pojos.errors.ApiFieldError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Tool {
    public static final String USER_NOT_FOUND = "El cliente no existe en el sistema";
    public static final String ID_ALREADY_EXIST = "El numero de identificacion ya se encuentra registrado.";
    public ValidateRuntimeException generateExceptionError(String msjError) {
        throw new ValidateRuntimeException(new ApiFieldError("2", Arrays.asList(msjError)));
    }

    public Map<String, Object> defaultResponse(){
        Map<String, Object> response = new HashMap<>();
        response.put("response", "ok");
        return response;
    }

}
