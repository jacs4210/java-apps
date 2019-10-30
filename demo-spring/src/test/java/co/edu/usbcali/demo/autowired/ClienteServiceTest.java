package co.edu.usbcali.demo.autowired;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContextAutowired.xml")
class ClienteServiceTest {

	@Autowired
	ClienteService clienteService;

	@Test
	void findById() {
		assertNotNull(clienteService, "clienteService nulo");
		Cliente cliente = clienteService.findById(89L);
		assertNotNull(cliente, "cliente nulo");
	}

	@Test
	void findAll() {
		assertNotNull(clienteService, "clienteService nulo");
		List<Cliente> listCliente = clienteService.findAll();
		assertNotNull(listCliente, "Lista es nula");
		assertFalse(listCliente.isEmpty());
	}

}
