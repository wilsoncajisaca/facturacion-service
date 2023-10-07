package com.mi.negocio.wilson.testfacturacion.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

/**
 * Data for save an address
 */
@Builder
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class RegisterAddressINP implements Serializable {
    @With
    private UUID id;

    @With
    private UUID clientId;

    @With
    private Integer mainAddress;

    @Size(min = 3, max = 100, message = "La provincia debe tener al menos 3 letras")
    @NotNull(message = "La provincia no puede ser vacia")
    @JsonProperty("province")
    @With
    private String province;

    @Size(min = 3, max = 100, message = "La ciudad debe tener al menos 3 letras")
    @NotNull(message = "La ciudad no puede ser vacia")
    @JsonProperty("city")
    @With
    private String city;

    @Size(min = 3, max = 250, message = "La referencia debe tener al menos 3 letras")
    @NotNull(message = "Debe ingresar una referencia")
    @JsonProperty("reference")
    @With
    private String referenceAddress;

    @Tolerate
    public RegisterAddressINP() {
        super();
    }
}
