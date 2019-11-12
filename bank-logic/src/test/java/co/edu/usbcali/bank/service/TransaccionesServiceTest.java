package co.edu.usbcali.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class TransaccionesServiceTest {

	@Autowired
	TransaccionesService transaccionesService;

	String cuenId = "4640-0341-9387-5781";
	String cuenDestino = "3992-3343-8699-1754";
	BigDecimal valor = new BigDecimal(150000);
	String usuUsuario = "lcasbolt8";

	@BeforeEach
	void beforeEach() {
		assertNotNull(transaccionesService, "transaccionesService es nulo");
	}

	@Test
	@DisplayName("retirar")
	void aTest() {
		try {
			Long numeroTransaccion = transaccionesService.retirar(cuenId, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "No retornó número de transaccion");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("consignar")
	void bTest() {
		try {
			Long numeroTransaccion = transaccionesService.consignar(cuenId, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "No retornó número de transaccion");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("transferir")
	void cTest() {
		try {
			Long numeroTransaccion = transaccionesService.transferir(cuenId, cuenDestino, valor, usuUsuario);
			assertNotNull(numeroTransaccion, "No retornó número de transaccion");
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

}
