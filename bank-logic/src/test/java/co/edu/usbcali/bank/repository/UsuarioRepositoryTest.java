package co.edu.usbcali.bank.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

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

import co.edu.usbcali.bank.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;
	
	private static String usuUsuario = "homerojsimpson";
	
	@BeforeEach
	void beforeEach() {
		assertNotNull(usuarioRepository, "usuarioRepository nulo");
		assertNotNull(tipoUsuarioRepository, "tipoUsuarioRepository nulo");
	}

	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	void aTest() {
	
		assertFalse(usuarioRepository.findById(usuUsuario).isPresent());
		
		Usuario usuario = new Usuario();
		usuario.setActivo("S");
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("contra123");
		usuario.setIdentificacion(new BigDecimal(1061321456));
		usuario.setNombre("Homero J. Simpson");

		assertTrue(tipoUsuarioRepository.findById(1L).isPresent());
		
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		
		usuarioRepository.save(usuario);

	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void bTest() {
		assertTrue(usuarioRepository.findById(usuUsuario).isPresent());
	}
	
	@Test
	@DisplayName("update")
	@Transactional(readOnly = true)
	void cTest() {
		assertTrue(usuarioRepository.findById(usuUsuario).isPresent());
		usuarioRepository.findById(usuUsuario).get().setActivo("N");
		
		usuarioRepository.save(usuarioRepository.findById(usuUsuario).get());
	}
	
	@Test
	@DisplayName("delete")
	@Transactional(readOnly = true)
	void dTest() {
		assertTrue(usuarioRepository.findById(usuUsuario).isPresent());
		
		usuarioRepository.delete(usuarioRepository.findById(usuUsuario).get());
	}

	@Test
	@DisplayName("findAll")
	@Transactional(readOnly = true)
	void eTest() {
		assertFalse(usuarioRepository.findAll().isEmpty());
	}
}
