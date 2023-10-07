package com.mi.negocio.wilson.testfacturacion.repositories;

import com.mi.negocio.wilson.testfacturacion.entities.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TestClientRepository {
    @Autowired
    private IClientRepository clientRepository;

    @Test
    public void testCreateClient(){
        Client clientEnt = Client.builder()
                .identificationType("CED")
                .identification("2222222222")
                .email("wilson@gmail.com")
                .name("Wilson")
                .surname("Cajisaca")
                .numberCellphone("0963805472")
                .status(1)
                .addresses(new HashSet<>())
                .build();
        Client clientCreated = clientRepository.save(clientEnt);
        assertThat(clientCreated).isNotNull();
    }
}
