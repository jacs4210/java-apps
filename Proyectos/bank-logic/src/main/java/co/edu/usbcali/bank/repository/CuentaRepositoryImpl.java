package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.Cuenta;

@Repository
@Scope("singleton")
public class CuentaRepositoryImpl implements CuentaRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Cuenta save(Cuenta entity) {
		if (!entityManager.contains(entity) && !findById(entity.getCuenId()).isPresent()) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
		return entity;
	}

	@Override
	public Optional<Cuenta> findById(String id) {
		Optional<Cuenta> cuentaOptional = Optional.ofNullable(entityManager.find(Cuenta.class, id));
		return cuentaOptional;
	}

	@Override
	public void delete(Cuenta entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(String id) {
		findById(id).ifPresent(cuenta -> delete(cuenta));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cuenta> findAll() {
		return entityManager.createQuery("FROM Cuenta").getResultList();
	}

}
