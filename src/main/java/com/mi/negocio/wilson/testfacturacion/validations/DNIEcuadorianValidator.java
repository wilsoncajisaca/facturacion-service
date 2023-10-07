package com.mi.negocio.wilson.testfacturacion.validations;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DNIEcuadorianValidator implements ConstraintValidator<EcuadorianDNI, String> {
    private static final Integer CEDULA_LENGTH = 10;
    private static final Integer RUC_LENGTH = 13;
    public static final Integer NUM_PROVINCIAS = 24;
    @Override
    public boolean isValid(String dni, ConstraintValidatorContext constraintValidatorContext) {
        boolean correctDNI = false;
        if (dni == null) {
            return false;
        }
        if (dni.length() == CEDULA_LENGTH) {
            int thirdDigit = Integer.parseInt(dni.substring(2, 3));
            if (thirdDigit <= 6) {
                int[] coefficientValidDni = {2, 1, 2, 1, 2, 1, 2, 1, 2};
                int verify = Integer.parseInt(dni.substring(9, 10));
                int sum = 0;
                int digit;
                for (int i = 0; i < (dni.length() - 1); i++) {
                    digit = Integer.parseInt(dni.substring(i, i + 1)) * coefficientValidDni[i];
                    sum += ((digit % 10) + (digit / 10));
                }
                if ((sum % 10 == 0) && (0 == verify)) {
                    correctDNI = true;
                } else correctDNI = (10 - (sum % 10)) == verify;
            }
        } else if (dni.length() == RUC_LENGTH) {
            //TODO: AQUI PUEDE IR LA VALIDACION DEL RUC, PARA ESTE CASO DE PRUEBA NO FUE NECESARIA LA INCORPORACION DEL MISMO.
            correctDNI = true;
        }
        return correctDNI;
    }
}
