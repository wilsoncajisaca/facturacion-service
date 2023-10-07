package com.mi.negocio.wilson.testfacturacion.service;

import com.mi.negocio.wilson.testfacturacion.entities.Client;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterAddressINP;
import com.mi.negocio.wilson.testfacturacion.pojos.RegisterClientINP;
import com.mi.negocio.wilson.testfacturacion.repositories.IClientRepository;
import com.mi.negocio.wilson.testfacturacion.service.impl.ClientServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TestClientService {
	@MockBean
	private IClientRepository clientRepository;
	@InjectMocks
	private ClientServiceImpl clientService;
	Client clientEnt;
	RegisterClientINP clientINP;
	@BeforeEach
	void setup (){
		clientEnt = Client.builder()
				.identificationType("CED")
				.identification("0106146137")
				.email("wilson@gmail.com")
				.name("Wilson")
				.surname("Cajisaca")
				.numberCellphone("0963805472")
				.status(1)
				.addresses(new HashSet<>())
				.build();

		clientINP = RegisterClientINP.builder()
				.identificationType("CED")
				.identification("0106146137")
				.email("wilson@gmail.com")
				.names("Wilson")
				.surname("Cajisaca")
				.numberCellphone("0963805472")
				.addressINP(new RegisterAddressINP())
				.build();
	}
	@Test
	public void createClient() throws Exception {
		given(clientRepository.findByIdentificationAndStatus(clientINP.getIdentification(),1))
				.willReturn(Optional.empty());
		given(clientRepository.save(clientEnt)).willReturn(clientEnt);
		Client clientCreated = clientService.createClient(clientINP);
		assertThat(clientCreated).isNotNull();
	}
}
