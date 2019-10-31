package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.dto.ResultadoAritmeticasDTO;

class JPQLTest {

	private static final Logger log = LoggerFactory.getLogger(JPQLTest.class);

	private EntityManagerFactory entityManagerFactory = null;
	private EntityManager entityManager = null;

	@BeforeEach
	void beforeEach() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");

		entityManager = entityManagerFactory.createEntityManager();
		assertNotNull(entityManager, "El entityManager es nulo");
	}

	@AfterEach
	void afterEach() {
		entityManager.close();
		entityManagerFactory.close();
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectAllCliente() {
		String jpql = "FROM Cliente";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> losClientes = query.getResultList();

//		for (Cliente cliente : losClientes) {
//			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
//		}

//		losClientes.forEach(cliente -> {
//			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
//		});

		losClientes.stream().filter(cliente -> cliente.getNombre().startsWith("J")).limit(10).forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectWhere() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.tdocId = :tdocId";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("tdocId", 1L).getResultList();

		// Programación funcional.
		losClientes.forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Consulta de clientes por nombre del tipo de documento")
	void selectWhereNombreTipoDocumento() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.nombre = :nombre";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("nombre", "CEDULA").getResultList();

		// Programación funcional.
		losClientes.forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Consulta de clientes por nombre con LIKE")
	void selectWhereNombreLike() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.nombre LIKE :letra";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("letra", "J%").getResultList();

		// Programación funcional.
		losClientes.forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Consulta de clientes con cuentas mayor a 4")
	void selectWhereCuentasMax4() {
		String jpql = "FROM Cliente cli WHERE size(cli.cuentas) > :count";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("count", 4).getResultList();

		// Programación funcional.
		losClientes.forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + ", Nombre: " + cliente.getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Consulta con operadores aritmeticos")
	void selectAritmeticas() {
		String jpql = "SELECT MAX(cue.saldo), MIN(cue.saldo), AVG(cue.saldo), COUNT(cue.saldo) FROM Cuenta cue";

		Object[] object = (Object[]) entityManager.createQuery(jpql).getSingleResult();

		log.info("MAX: " + object[0]);
		log.info("MIN: " + object[1]);
		log.info("AVG: " + object[2]);
		log.info("COUNT: " + object[3]);

	}

	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Consulta con operadores aritmeticos DTO")
	void selectAritmeticasDTO() {
		String jpql = "SELECT " + "new co.edu.usbcali.bank.dto.ResultadoAritmeticasDTO(" + "MAX(cue.saldo),"
				+ "MIN(cue.saldo)," + "AVG(cue.saldo)," + "COUNT(cue.saldo)" + ") FROM Cuenta cue";

		ResultadoAritmeticasDTO resultadoAritmeticasDTO = (ResultadoAritmeticasDTO) entityManager.createQuery(jpql)
				.getSingleResult();

		log.info("MAX: " + resultadoAritmeticasDTO.getMax());
		log.info("MIN: " + resultadoAritmeticasDTO.getMin());
		log.info("AVG: " + resultadoAritmeticasDTO.getAvg());
		log.info("COUNT: " + resultadoAritmeticasDTO.getCount());
	}

}
