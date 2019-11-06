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

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.repository.ClienteRepository;
import co.edu.usbcali.bank.repository.TipoDocumentoRepository;

@Service
@Scope("singleton")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	private Validator validator;

	public void validar(Cliente cliente) throws Exception {
		try {

			if (cliente == null) {
				throw new Exception("El cliente es nulo");
			}

			Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
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
	public Cliente save(Cliente entity) throws Exception {

		// Se encarga de validar que el cliente ingresado contemple lo minimo necesario
		// para poder tratarlo.
		validar(entity);

		if (clienteRepository.findById(entity.getClieId()).isPresent() == true) {
			throw new Exception("El cliente con Id: " + entity.getClieId() + " ya existe");
		}

		if (entity.getTipoDocumento() == null || entity.getTipoDocumento().getTdocId() == null
				|| tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con Id: " + entity.getTipoDocumento().getTdocId() + " no existe");
		}

		return clienteRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente entity) throws Exception {

		// Se encarga de validar que el cliente ingresado contemple lo minimo necesario
		// para poder tratarlo.
		validar(entity);

		if (clienteRepository.findById(entity.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con Id: " + entity.getClieId() + " no existe");
		}

		if (entity.getTipoDocumento() == null || entity.getTipoDocumento().getTdocId() == null
				|| tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("El tipo de documento con Id: " + entity.getTipoDocumento().getTdocId() + " no existe");
		}

		return clienteRepository.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente entity) throws Exception {
		// Se encarga de validar que el cliente ingresado contemple lo minimo necesario
		// para poder tratarlo.
		validar(entity);

		if (clienteRepository.findById(entity.getClieId()).isPresent() == false) {
			throw new Exception("El cliente con Id: " + entity.getClieId() + " no existe");
		}

		entity = clienteRepository.findById(entity.getClieId()).get();

		// Si el cliente tiene cuentas registradas, debe de elevar una excepci�n.
		if (entity.getCuentaRegistradas().size() > 0) {
			throw new Exception("El cliente con Id: " + entity.getClieId() + " tiene cuentas registradas");
		}

		// Si el cliente tiene cuentas asociadas, debe de elevar una excepci�n.
		if (entity.getCuentas().size() > 0) {
			throw new Exception("El cliente con Id: " + entity.getClieId() + " tiene cuentas asociadas");
		}

		clienteRepository.delete(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {

		if (id == null || id < 0) {
			throw new Exception("El id del cliente es obligatorio, no puede ser nulo o menor a 1");
		}

		if (findById(id).isPresent() == false) {
			throw new Exception("El cliente con Id: " + id + " no existe");
		}

		delete(findById(id).get());

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

}
