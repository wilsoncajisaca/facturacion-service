package com.mi.negocio.wilson.testfacturacion.service.impl;

import com.mi.negocio.wilson.testfacturacion.entities.Address;
import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.mappers.AddressMapper;
import com.mi.negocio.wilson.testfacturacion.mappers.ClientMapper;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.pojos.UpdateClientINP;
import com.mi.negocio.wilson.testfacturacion.repositories.IClientRepository;
import com.mi.negocio.wilson.testfacturacion.service.IClientService;
import com.mi.negocio.wilson.testfacturacion.service.tools.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service implementation for managing {@link Client}
 *
 * @author wcajisaca
 */
@Slf4j
@Service
public class ClientServiceImpl extends Tool implements IClientService {
    @Autowired
    private IClientRepository clientRepository;

    /**
     * {@inheritDoc}
     * @param filter
     * @return
     */
    @Override
    public Client createClient(RegisterClientINP clientINP) throws Exception {
        log.info("Service to save a client");
        this.existClientByIdentification(clientINP.getIdentification());
        Client clientEntity = ClientMapper.toEntityRegister(clientINP);
        return this.setAddressClient(clientINP, this.clientRepository.save(clientEntity));
    }

    /**
     * {@inheritDoc}
     * @param filter
     * @return
     */
    @Override
    public Client updateClient(UpdateClientINP clientINP) {
        log.info("Service to save a client");
        Client clientUpd = this.getClientByIdentification(clientINP.getIdentification());
        Client clientEntity = ClientMapper.clientUpd(clientUpd, clientINP);
        return this.clientRepository.save(clientEntity);
    }

    /**
     * {@inheritDoc}
     * @param filter
     * @return
     */
    @Override
    public Map<String, Object> deleteClient(UUID filter) {
        this.clientRepository.findById(filter)
                .ifPresentOrElse(client ->this.clientRepository.deleteById(filter),
                    () -> this.generateExceptionError(USER_NOT_FOUND)
                );
        return defaultResponse();
    }

    /**
     * {@inheritDoc}
     * @param filter
     * @return
     */
    @Override
    public Set<Client> getClientById(String filter) {
        Set<Client> clients = this.clientRepository.findByStatusAndIdentificationOrSurname(1, filter);
        if(clients.isEmpty()){
            this.generateExceptionError(USER_NOT_FOUND);
        }
        return clients;
    }

    /**
     * Search and Validate client into database for identifier
     *
     * @param identification Identifier
     */
    private void existClientByIdentification(String identification) {
        this.clientRepository.findByIdentificationAndStatus(identification, 1)
                .stream().findAny().ifPresent(client ->
                        this.generateExceptionError(ID_ALREADY_EXIST));
    }
    private Client getClientByIdentification(String identifier){
        return this.clientRepository.findByIdentificationAndStatus(identifier, 1)
                .orElseThrow(() -> this.generateExceptionError(USER_NOT_FOUND));
    }

    /**
     * Permite crear la direccion matriz
     * @param clientINP
     * @param clientEntity
     * @return
     */
    private Client setAddressClient(RegisterClientINP clientINP, Client clientEntity){
        Address addressEntity = AddressMapper.toAddressEntityRegister(clientINP.getAddressINP(), clientEntity);
        addressEntity.setMainAddress(1);
        Set<Address> addresses = new HashSet<>();
        addresses.add(addressEntity);
        clientEntity.setAddresses(addresses);
        return this.clientRepository.save(clientEntity);
    }
}