package com.mi.negocio.wilson.testfacturacion.controllers;

import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.service.IAddressService;
import com.mi.negocio.wilson.testfacturacion.validations.Validations;
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

    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createClient(@Valid @RequestBody RegisterAddressINP addressINP, Errors errors) throws Exception {
        Validations.validateFieldRequest(errors);
        log.debug("REST request to create an address : {}", addressINP);
        return ResponseEntity.ok().body(this.addressService.createAddressForClient(addressINP));
    }

    @GetMapping(value = "getByClientId/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getByClientId(@PathVariable UUID filter) {
        return ResponseEntity.ok().body(this.addressService.getAddressByClientId(filter));
    }
}
