package co.edu.usbcali.bank.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.bank.dto.ResultadoAritmeticasDTO;

class JPQLTest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;

	private final static Logger log = LoggerFactory.getLogger(JPQLTest.class);

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

	@SuppressWarnings("unchecked")
	@Test
	void selectAritmeticasDTO() {
		String jpql = "SELECT "
				+ "new co.edu.usbcali.bank.dto.ResultadoAritmeticasDTO("
				+ "MAX(cue.saldo), "
				+ "MIN(cue.saldo), "
				+ "AVG(cue.saldo), "
				+ "COUNT(cue.saldo)"
				+ ") FROM Cuenta cue";

		ResultadoAritmeticasDTO resultadoAritmeticasDTO = (ResultadoAritmeticasDTO) entityManager.createQuery(jpql)
				.getSingleResult();

		log.info("MAX: " + resultadoAritmeticasDTO.getMax());
		log.info("MIN: " + resultadoAritmeticasDTO.getMin());
		log.info("AVG: " + resultadoAritmeticasDTO.getAvg());
		log.info("CON: " + resultadoAritmeticasDTO.getCount());

	}

	@SuppressWarnings("unchecked")
	@Test
	void selectAritmeticas() {
		String jpql = "SELECT MAX(cue.saldo), MIN(cue.saldo), AVG(cue.saldo), COUNT(cue.saldo) FROM Cuenta cue";

		Object[] objects = (Object[]) entityManager.createQuery(jpql).getSingleResult();

		log.info("MAX: " + objects[0]);
		log.info("MIN: " + objects[1]);
		log.info("AVG: " + objects[2]);
		log.info("CON: " + objects[3]);

	}

	@SuppressWarnings("unchecked")
	@Test
	void selectClienteConMasDe4Cuentas() {
		String jpql = "SELECT cli FROM Cliente cli WHERE size(cli.cuentas)>4";

		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();

		losClientes.forEach(cliente -> {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
			log.info("Cuentas: \t" + cliente.getCuentas().size());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectWhereLike() {
		String jpql = "FROM Cliente cli WHERE cli.nombre LIKE :nombre";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("nombre", "%J%").getResultList();

		losClientes.forEach(cliente -> {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
			log.info("Tipo documento: \t" + cliente.getTipoDocumento().getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectWhereByNombreTipoDocumento() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.nombre=:nombre";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("nombre", "CEDULA").getResultList();

		losClientes.forEach(cliente -> {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
			log.info("Tipo documento: \t" + cliente.getTipoDocumento().getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectWhere() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.tdocId=:tdocId";

		List<Cliente> losClientes = entityManager.createQuery(jpql).setParameter("tdocId", 1L).getResultList();

		losClientes.forEach(cliente -> {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
			log.info("Tipo documento: \t" + cliente.getTipoDocumento().getNombre());
		});
	}

	@SuppressWarnings("unchecked")
	@Test
	void selectAllCliente() {
		String jpql = "from Cliente";
		Query query = entityManager.createQuery(jpql);
		List<Cliente> losClientes = query.getResultList();

		for (Cliente cliente : losClientes) {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
		}

		losClientes.forEach(cliente -> {
			log.info("Id: \t" + cliente.getClieId());
			log.info("Nombre: \t" + cliente.getNombre());
		});
	}

}
