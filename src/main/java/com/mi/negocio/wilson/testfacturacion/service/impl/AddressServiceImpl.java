package com.mi.negocio.wilson.testfacturacion.service.impl;

import com.mi.negocio.wilson.testfacturacion.entities.Address;
import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.mappers.AddressMapper;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.repositories.IAddressRepository;
import com.mi.negocio.wilson.testfacturacion.repositories.IClientRepository;
import com.mi.negocio.wilson.testfacturacion.service.IAddressService;
import com.mi.negocio.wilson.testfacturacion.service.tools.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Service implementation for managing {@link Address}
 *
 * @author wcajisaca
 */
@Slf4j
@Service
public class AddressServiceImpl extends Tool implements IAddressService {
    @Autowired
    @Lazy
    private IAddressRepository addressRepository;
    @Autowired
    @Lazy
    private IClientRepository clientRepository;
    @Override
    @Transactional
    public Map<String, Object> createAddressForClient(RegisterAddressINP addressINP) {
        this.clientRepository.findByIdAndStatus(addressINP.getClientId(), 1)
                .ifPresentOrElse(client -> this.createAddress(addressINP,client),
                        ()-> this.generateExceptionError(USER_NOT_FOUND));
        return defaultResponse();
    }

    @Override
    public Set<Address> getAddressByClientId(UUID id) {
        return this.addressRepository.findByIdClientAndStatus(id, 1);
    }

    private void createAddress(RegisterAddressINP addressINP, Client client){
        Address address = AddressMapper.toAddressEntityRegister(addressINP, client);
        if(addressINP.getMainAddress() == 1){
            this.addressRepository.updateAddressMatriz(client.getId());
        }else if(addressINP.getMainAddress() != 1){
            address.setMainAddress(0);
        }
        this.addressRepository.save(address);
    }
}