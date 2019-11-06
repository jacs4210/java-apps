package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTestMockito {

	private final static Long clieId = 7890L;

	@InjectMocks
	ClienteService clienteService = new ClienteServiceImpl();

	@Mock
	TipoDocumentoRepository tipoDocumentoRepository;

	@Mock
	ClienteRepository clienteRepository;

	@Mock
	Validator validator;

	Cliente cliente;

	@BeforeEach
	void beforeEach() {
		assertNotNull(clienteService, "El clienteService es nulo");
		assertNotNull(tipoDocumentoRepository, "El tipoDocumentoRepository es nulo");
		TipoDocumento tipoDocumento = new TipoDocumento();
		tipoDocumento.setTdocId(1L);
		tipoDocumento.setActivo("S");
		tipoDocumento.setNombre("CEDULA");

		when(tipoDocumentoRepository.findById(1L)).thenReturn(Optional.ofNullable(tipoDocumento));

		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("homeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("8388378");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent());

		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		cliente.setCuentaRegistradas(new ArrayList<>());
		cliente.setCuentas(new ArrayList<>());
	}

	@Test
	@DisplayName("save")
	void aTest() {
		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("homeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("8388378");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent());

		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		when(clienteRepository.findById(clieId)).thenReturn(Optional.ofNullable(cliente));
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);

		assertTrue(clienteOptional.isPresent(), "El cliente con id: " + clieId + " no existe");
	}

	@Test
	@DisplayName("update")
	void cTest() {
		when(clienteRepository.findById(clieId)).thenReturn(Optional.ofNullable(cliente));
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);

		assertTrue(clienteOptional.isPresent(), "El cliente con id: " + clieId + " no existe");

		Cliente cliente = clienteOptional.get();
		cliente.setActivo("N");

		try {
			clienteService.update(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		when(clienteRepository.findById(clieId)).thenReturn(Optional.ofNullable(cliente));
		try {
			clienteService.deleteById(clieId);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

}
