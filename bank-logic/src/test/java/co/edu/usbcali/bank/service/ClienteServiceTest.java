package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteServiceTest {

	private final static Long clieId = 7890L;

	@Autowired
	ClienteService clienteService;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@BeforeEach
	void beforeEach() {
		assertNotNull(clienteService, "El clienteService es nulo");
		assertNotNull(tipoDocumentoRepository, "El tipoDocumentoRepository es nulo");
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
		Optional<Cliente> clienteOptional = clienteService.findById(clieId);

		assertTrue(clienteOptional.isPresent(), "El cliente con id: " + clieId + " no existe");
	}

	@Test
	@DisplayName("update")
	void cTest() {
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
		try {
			clienteService.deleteById(clieId);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

}
