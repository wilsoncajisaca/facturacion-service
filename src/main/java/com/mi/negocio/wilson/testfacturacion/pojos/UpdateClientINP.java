package com.mi.negocio.wilson.testfacturacion.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mi.negocio.wilson.testfacturacion.validations.EcuadorianDNI;
import com.mi.negocio.wilson.testfacturacion.validations.Validations;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

/**
 * Data for update a client
 */
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class UpdateClientINP {
    @JsonProperty("id")
    @With
    private UUID id;

    @Size(min = 3,max = 3, message = "El tipo de identificacion debe contener 3 caracteres. Ejm: CED/RUC")
    @With
    private String identificationType;

    @Size(min = 10, max = 13, message = "La IDENTIFICACION debe contener entre 10 y 13 caracteres")
    @EcuadorianDNI
    @JsonProperty("identification")
    @With
    private String identification;

    @JsonProperty("name")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Invalid Name")
    @With
    private String names;

    @JsonProperty("surname")
    @Pattern(regexp = Validations.REGEX_NAME_VALIDATION, message = "Surname invalid")
    @With
    private String surname;

    @Size(min = 5, max = 250, message = "El correo debe contener entre 5 a 250 caracteres")
    @Pattern(regexp = Validations.REGEX_MAIL_VALIDATION, message = "El correo es invalido")
    @JsonProperty("email")
    @With
    private String email;

    @Pattern(regexp = Validations.REGEX_CELL_VALIDATION, message = "Numero celular invalido")
    @JsonProperty("numberPhone")
    @With
    private String numberCellphone;

    @Tolerate
    UpdateClientINP (){
        super();
    }
}
