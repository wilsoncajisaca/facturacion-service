package com.mi.negocio.wilson.testfacturacion.service;

import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.pojos.UpdateClientINP;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Service interface for managing
 * {@link com.mi.negocio.wilson.testfacturacion.entities.Client}
 *
 * @author wcajisaca
 */
public interface IClientService {

    /**
     * Save a new client
     *
     * @param clientINP the entity data to save
     * @return the persisted entity
     */
    Client createClient(RegisterClientINP clientINP) throws Exception;

    /**
     * update a client with complete information
     *
     * @param clientINP the entity data to update
     * @return the persisted entity
     */
    Client updateClient(UpdateClientINP clientINP);
    /**
     * Delete client by id
     * @param filter id for delete an entity
     * @return a information map
     */
    Map<String,Object> deleteClient(UUID filter);
    /**
     * Get client by identification
     * @return the persisted entity
     */
    Set<Client> getClientById(String id);
}