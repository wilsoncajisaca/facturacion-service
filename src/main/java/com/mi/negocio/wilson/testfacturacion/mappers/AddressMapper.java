package com.mi.negocio.wilson.testfacturacion.mappers;

import com.mi.negocio.wilson.testfacturacion.entities.Address;
import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;

/**
 * @author wcajisaca
 */
public class AddressMapper {
    /**
     * Cast Request to entity
     *
     * @param addressINP Request
     * @return Entity Address
     */
    public static Address toAddressEntityRegister(RegisterAddressINP addressINP, Client client) {
        return Address.builder()
                .idClient(client.getId())
                .client(client)
                .mainAddress(addressINP.getMainAddress())
                .province(addressINP.getProvince())
                .city(addressINP.getCity())
                .referenceAddress(addressINP.getReferenceAddress())
                .build();
    }
}
