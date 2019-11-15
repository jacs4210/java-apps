package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsuarioCRUDTest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;
	private static String usuUsuario = "homerojsimpson";

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

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);

		usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("contra123");
		usuario.setIdentificacion(new BigDecimal(1061321456));
		usuario.setNombre("Homero J. Simpson");

		TipoUsuario tipoUsuario = entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsuario, "El tipo de usuario es nulo");
		usuario.setTipoUsuario(tipoUsuario);

		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();

	}
	
	@Test
	@DisplayName("findById")
	void bTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);

	}
	
	@Test
	@DisplayName("update")
	void cTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);

		usuario.setActivo("N");
		
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();

	}
	
	@Test
	@DisplayName("delete")
	void dTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);
		
		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();

	}

}
