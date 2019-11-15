package co.edu.usbcali.demo.autowired;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContextAutowired.xml")
class ClienteDAOTest {

	@Autowired
	ClienteDAO clienteDAO;

	@Test
	void test() {
		assertNotNull(clienteDAO, "clienteDAO nulo");
	}

}
