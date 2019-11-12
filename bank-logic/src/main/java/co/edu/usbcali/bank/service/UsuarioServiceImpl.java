package co.edu.usbcali.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.TipoUsuarioRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoUsuarioRepository tipoUsuarioRepository;

	@Autowired
	private Validator validator;

	public void validar(Usuario usuario) throws Exception {
		try {

			if (usuario == null) {
				throw new Exception("El usuario es nulo");
			}

			Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario save(Usuario entity) throws Exception {
		validar(entity);
		if (usuarioRepository.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " ya existe.");
		}
		if (entity.getTipoUsuario() == null || entity.getTipoUsuario().getTiusId() == null
				|| !tipoUsuarioRepository.findById(entity.getTipoUsuario().getTiusId()).isPresent()) {
			throw new Exception("El tipo de usuario con id: " + entity.getTipoUsuario().getTiusId() + " no existe.");
		}

		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario entity) throws Exception {
		validar(entity);
		if (!usuarioRepository.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " no existe.");
		}
		if (entity.getTipoUsuario() == null || entity.getTipoUsuario().getTiusId() == null
				|| !tipoUsuarioRepository.findById(entity.getTipoUsuario().getTiusId()).isPresent()) {
			throw new Exception("El tipo de usuario con id: " + entity.getTipoUsuario().getTiusId() + " no existe.");
		}

		return usuarioRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario entity) throws Exception {
		validar(entity);

		if (!usuarioRepository.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " no existe.");
		}
		entity = usuarioRepository.findById(entity.getUsuUsuario()).get();
		if (entity.getTransaccions().size() > 0) {
			throw new Exception("El usuario con id: " + entity.getUsuUsuario() + " tiene transacciones asociadas.");
		}
		usuarioRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		if (id == null || id.isEmpty()) {
			throw new Exception("El id es obligatorio, no puede ser nulo o vacío");
		}
		if (!findById(id).isPresent()) {
			throw new Exception("El usuario con id: " + id + " no existe.");
		}
		delete(findById(id).get());

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(String id) {
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
}
