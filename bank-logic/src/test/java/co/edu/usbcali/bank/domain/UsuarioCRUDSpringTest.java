package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

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
class UsuarioCRUDSpringTest {

	@PersistenceContext
	EntityManager entityManager;

	private static String usuUsuario = "homerojsimpson";

	@Test
	void test() {
		assertNotNull(entityManager, "El entityManager es nulo");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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

		entityManager.persist(usuario);
	}

	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);
	}

	@Test
	@DisplayName("update")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void cTest() {

		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);

		usuario.setActivo("N");

		entityManager.merge(usuario);
	}

	@Test
	@DisplayName("delete")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void dTest() {
		Usuario usuario = entityManager.find(Usuario.class, usuUsuario);
		assertNotNull(usuario, "Ya existe un usuario con el id: " + usuUsuario);

		entityManager.remove(usuario);
	}

}
