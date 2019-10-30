package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteCRUDTest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	private static Long clieId = 6767L;

	@BeforeEach
	void beforeEach() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");

		entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "El entityManager es nulo");
	}
	
	@AfterEach
	void AfterEach() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@Test
	@DisplayName("save")
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

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

	}
	
	@Test
	@DisplayName("findById")
	void bTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);

	}
	
	@Test
	@DisplayName("update")
	void cTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);

		cliente.setActivo("N");
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();

	}
	
	@Test
	@DisplayName("delete")
	void dTest() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "Ya existe un cliente con el id: " + clieId);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();

	}

}
