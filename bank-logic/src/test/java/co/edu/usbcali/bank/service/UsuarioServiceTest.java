package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioServiceTest {

	private final static String usuId = "homerojsimpson";

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@BeforeEach
	void beforeEach() {
		assertNotNull(usuarioService, "El usuarioService es nulo");
		assertNotNull(tipoUsuarioRepository, "El tipoUsuarioRepository es nulo");
	}

	@Test
	@DisplayName("save")
	void aTest() {
		Usuario usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuId);
		usuario.setClave("contra123");
		usuario.setIdentificacion(new BigDecimal(1061321456));
		usuario.setNombre("Homero J. Simpson");

		assertTrue(tipoUsuarioRepository.findById(1L).isPresent());
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());

		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("findById")
	void bTest() {
		Optional<Usuario> usuarioOptional = usuarioService.findById(usuId);
		assertTrue(usuarioOptional.isPresent(), "El usuario con id: " + usuId + " no existe.");
	}

	@Test
	@DisplayName("update")
	void cTest() {
		Optional<Usuario> usuarioOptional = usuarioService.findById(usuId); 
		assertTrue(usuarioOptional.isPresent(), "El usuario con id: " + usuId + " no existe.");

		Usuario usuario = usuarioOptional.get();
		usuario.setActivo("N");

		try {
			usuarioService.update(usuario);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("delete")
	void dTest() {
		try {
			usuarioService.deleteById(usuId);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
}
