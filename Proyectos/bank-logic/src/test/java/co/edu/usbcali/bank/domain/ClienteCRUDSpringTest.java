package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteCRUDSpringTest {

	@PersistenceContext
	EntityManager entityManager;

	private static Long clieId = 6767L;

	@Test
	void test() {
		assertNotNull(entityManager, "entityManager nulo");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "Ya existe un cliente con el id: " + clieId);

		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("homeroJSimpson@gmail.com");
		cliente.setNombre("Homero J Simpson");
		cliente.setTelefono("8388378");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.persist(cliente);

	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);

	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void cTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);

		cliente.setActivo("N");
		
		entityManager.persist(cliente);

	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);
		
		entityManager.remove(cliente);

	}
}
