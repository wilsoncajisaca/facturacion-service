package com.mi.negocio.wilson.testfacturacion.mappers;

import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.pojos.UpdateClientINP;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Mapping entity client
 */
public class ClientMapper {
    /**
     * Cast Request to entity
     *
     * @param clientINP Request
     * @return Entity Client
     */
    public static Client toEntityRegister(RegisterClientINP clientINP) {
        return Client.builder()
                .identificationType(clientINP.getIdentificationType())
                .identification(clientINP.getIdentification())
                .name(clientINP.getNames())
                .surname(clientINP.getSurname())
                .email(clientINP.getEmail())
                .numberCellphone(clientINP.getNumberCellphone())
                .build();
    }

    public static Client clientUpd(Client client, UpdateClientINP clientINP){
        Client clientEnt = SerializationUtils.clone(client);
        if(clientINP.getIdentificationType() != null){
            clientEnt.setIdentificationType(clientINP.getIdentificationType());
        }
        if(clientINP.getIdentification() != null){
            clientEnt.setIdentification(clientINP.getIdentification());
        }
        if(clientINP.getNames() != null){
            clientEnt.setName(clientINP.getNames());
        }
        if(clientINP.getSurname() != null){
            clientEnt.setSurname(clientINP.getSurname());
        }
        if(clientINP.getEmail() != null){
            clientEnt.setEmail(clientINP.getEmail());
        }
        if(clientINP.getNumberCellphone() != null){
            clientEnt.setNumberCellphone(clientINP.getNumberCellphone());
        }
        return clientEnt;
    }
}