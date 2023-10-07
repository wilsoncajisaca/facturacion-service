package com.mi.negocio.wilson.testfacturacion.controllers;

import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.service.IAddressService;
import com.mi.negocio.wilson.testfacturacion.validations.Validations;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/address/")
public class AddressController {
    @Autowired
    private IAddressService addressService;

    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> test() {
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("response", "OK");
        return ResponseEntity.ok().body(resp);
    }

    /**
     * {@code POST  /create} : Create a address for client
     * Save data of a client
     *
     * @param addressINP Data address for save
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body of new address
     */
    @Operation(summary = "Permite crear una direccion Nueva")
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAddress(@Valid @RequestBody RegisterAddressINP addressINP, Errors errors) throws Exception {
        Validations.validateFieldRequest(errors);
        log.debug("REST request to create an address : {}", addressINP);
        return ResponseEntity.ok().body(this.addressService.createAddressForClient(addressINP));
    }

    @Operation(summary = "Permite buscar las direcciones por cliente")
    @GetMapping(value = "getByClientId/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByClientId(@PathVariable UUID filter) {
        return ResponseEntity.ok().body(this.addressService.getAddressByClientId(filter));
    }
}
