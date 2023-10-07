package com.mi.negocio.wilson.testfacturacion.service;

import com.mi.negocio.wilson.testfacturacion.entities.Address;
import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Service interface for managing
 * {@link com.mi.negocio.wilson.testfacturacion.entities.Address}
 *
 * @author wcajisaca
 */
public interface IAddressService {
    /**
     * Save a new client
     *
     * @param addressINP the entity data to save
     * @return the persisted entity
     */
    Map<String, Object> createAddressForClient(RegisterAddressINP addressINP) throws Exception;
    /**
     * Get client by identification
     * @return the persisted entity
     */
    Set<Address> getAddressByClientId(UUID id);
}
