package com.mi.negocio.wilson.testfacturacion.controllers;

import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.pojos.UpdateClientINP;
import com.mi.negocio.wilson.testfacturacion.service.IClientService;
import com.mi.negocio.wilson.testfacturacion.validations.Validations;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/v1/client/")
public class ClientController {
    @Autowired
    @Lazy
    private IClientService clientService;
    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> test() {
        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("response", "OK");
        return ResponseEntity.ok().body(resp);
    }

    /**
     * {@code GET  /getById} : get client by id
     * @return the {@link ResponseEntity} with status {@code 202 (OK)} and with body of a client
     */
    @Operation(summary = "Permite crear buscar un cliente por su identificacion o nombre")
    @GetMapping(value = "getById/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getClientById(@PathVariable String filter) throws Exception {
        return ResponseEntity.ok().body(this.clientService.getClientById(filter));
    }

    /**
     * {@code POST  /create} : Create a client
     * Save data of a client
     *
     * @param clientINP Data client for save
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body of new client
     */
    @Operation(summary = "Permite crear un nuevo cliente")
    @PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createClient(@Valid @RequestBody RegisterClientINP clientINP, Errors errors) throws Exception {
        Validations.validateFieldRequest(errors);
        log.debug("REST request to create an client : {}", clientINP);
        return ResponseEntity.ok().body(this.clientService.createClient(clientINP));
    }

    /**
     * {@code PUT  /update} : update a client
     * Update data of an client
     *
     * @param updClientINP Data {@link com.mi.negocio.wilson.testfacturacion.entities.Client} for update
     * @return the {@link ResponseEntity} with status {@code 202 (OK)} and with body of a client
     */
    @Operation(summary = "Permite actualizar un cliente")
    @PutMapping(value = "update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateClient(@Valid @RequestBody UpdateClientINP updClientINP, Errors errors) throws Exception {
        Validations.validateFieldRequest(errors);
        log.debug("REST request to delete an client : {}", updClientINP);
        return ResponseEntity.ok().body(this.clientService.updateClient(updClientINP));
    }

    @Operation(summary = "Permite Eliminar un nuevo Cliente")
    @DeleteMapping(value = "delete/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteClient(@PathVariable UUID filter) throws Exception {
        log.debug("REST request to delete an client : {}", filter);
        return ResponseEntity.ok().body(this.clientService.deleteClient(filter));
    }
}