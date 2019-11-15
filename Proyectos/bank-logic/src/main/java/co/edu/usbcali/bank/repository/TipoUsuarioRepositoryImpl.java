package co.edu.usbcali.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.bank.domain.TipoUsuario;

@Repository
@Scope("singleton")
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public TipoUsuario save(TipoUsuario entity) {
		if (!entityManager.contains(entity)) {
			entityManager.persist(entity);
		} else {
			entityManager.merge(entity);
		}
		return entity;
	}

	@Override
	public Optional<TipoUsuario> findById(Long id) {
		Optional<TipoUsuario> tipoUsuarioOptional = Optional.ofNullable(entityManager.find(TipoUsuario.class, id));
		return tipoUsuarioOptional;
	}

	@Override
	public void delete(TipoUsuario entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		findById(id).ifPresent(tipoUsuario -> delete(tipoUsuario));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoUsuario> findAll() {
		return entityManager.createQuery("FROM TipoUsuario").getResultList();
	}

}
