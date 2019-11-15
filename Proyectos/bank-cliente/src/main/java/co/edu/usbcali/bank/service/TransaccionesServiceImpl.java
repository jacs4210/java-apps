package co.edu.usbcali.bank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.bank.domain.Cuenta;
import co.edu.usbcali.bank.domain.TipoTransaccion;
import co.edu.usbcali.bank.domain.Transaccion;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.repository.CuentaRepository;
import co.edu.usbcali.bank.repository.TipoTransaccionRepository;
import co.edu.usbcali.bank.repository.TransaccionRepository;
import co.edu.usbcali.bank.repository.UsuarioRepository;

@Service
@Scope("singleton")
public class TransaccionesServiceImpl implements TransaccionesService {

	private final static String cuentaBanco = "9999-9999-9999-9999";
	
	@Autowired
	CuentaRepository cuentaRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	TipoTransaccionRepository tipoTransaccionRepository;

	@Autowired
	TransaccionRepository transaccionRepository;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {

		validar(cuenId, valor, usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		if (cuenta.getSaldo().compareTo(valor) == -1) {
			throw new Exception("No se puede realizar el retiro por fondos insuficientes.");
		}

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(1L).get();
		
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
		cuentaRepository.save(cuenta);
		
		return transaccionRepository.save(transaccion).getTranId();

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);
		
		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(2L).get();
		
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);
		
		return transaccionRepository.save(transaccion).getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long transferir(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		
		if (cuenIdOrigen == null || cuenIdDestino == null ) {
			throw new Exception("La cuenta origen y la cuenta destino son obligatorias.");
		}
		if (cuenIdOrigen == cuenIdDestino) {
			throw new Exception("La cuenta origen y la cuenta destino son iguales.");
		}
		
		retirar(cuenIdOrigen, valor, usuUsuario);
		consignar(cuenIdDestino, valor, usuUsuario);
		retirar(cuenIdOrigen, new BigDecimal(2000), usuUsuario);
		consignar(cuentaBanco, new BigDecimal(2000), usuUsuario);
		
		Cuenta cuenta = cuentaRepository.findById(cuenIdOrigen).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(3L).get();
		
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		
		transaccion.setValor(valor);
		
		return transaccionRepository.save(transaccion).getTranId();
	}

	private void validar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		if (cuenId == null || cuenId.trim().equals("")) {
			throw new Exception("El número de la cuenta es obligatorio.");
		}
		if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
			throw new Exception("El valor debe ser positivo.");
		}
		if (usuUsuario == null || usuUsuario.isEmpty()) {
			throw new Exception("El usuario es obligatorio.");
		}

		Optional<Cuenta> cuentaOptional = cuentaRepository.findById(cuenId);
		if (!cuentaOptional.isPresent()) {
			throw new Exception("La cuenta con id " + cuenId + " no existe.");
		}
		if (cuentaOptional.get().getActiva().equals("N")) {
			throw new Exception("La cuenta se encuentra inactiva.");
		}

		Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuUsuario);
		if (!usuarioOptional.isPresent()) {
			throw new Exception("El usuario con id " + usuUsuario + " no existe.");
		}
		if (usuarioOptional.get().getActivo().equals("N")) {
			throw new Exception("La usuario se encuentra inactivo.");
		}
	}

}
