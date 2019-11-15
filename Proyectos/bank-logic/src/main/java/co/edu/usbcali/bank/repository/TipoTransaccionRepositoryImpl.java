package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoTransaccion;

@Repository
@Scope("singleton")
public class TipoTransaccionRepositoryImpl implements TipoTransaccionRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public TipoTransaccion save(TipoTransaccion entity) {
		if (!entityManager.contains(entity) && !findById(entity.getTitrId()).isPresent()) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
		return entity;
	}

	@Override
	public Optional<TipoTransaccion> findById(Long id) {
		Optional<TipoTransaccion> tipoTransaccionOptional = Optional
				.ofNullable(entityManager.find(TipoTransaccion.class, id));
		return tipoTransaccionOptional;
	}

	@Override
	public void delete(TipoTransaccion entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(tipoTransaccion -> delete(tipoTransaccion));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTransaccion> findAll() {
		return entityManager.createQuery("FROM TipoTransaccion").getResultList();
	}

}
