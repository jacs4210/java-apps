package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cliente;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteRepositoryTest {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	private static Long clieId = 6767L;
	
	@BeforeEach
	void beforeEach() {
		assertNotNull(clienteRepository, "clienteRepository nulo");
		assertNotNull(tipoDocumentoRepository, "tipoDocumentoRepository nulo");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
	
		assertFalse(clienteRepository.findById(clieId).isPresent());
		
		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("homeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("8388378");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent());
		
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		
		clienteRepository.save(cliente);

	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertTrue(clienteRepository.findById(clieId).isPresent());
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void cTest() {
		assertTrue(clienteRepository.findById(clieId).isPresent());
		clienteRepository.findById(clieId).get().setActivo("N");
		
		clienteRepository.save(clienteRepository.findById(clieId).get());
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dTest() {
		assertTrue(clienteRepository.findById(clieId).isPresent());
		
		clienteRepository.delete(clienteRepository.findById(clieId).get());
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertFalse(clienteRepository.findAll().isEmpty());
	}
}
