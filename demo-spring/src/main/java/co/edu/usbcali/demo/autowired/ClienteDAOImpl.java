package co.edu.usbcali.demo.autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

// Patrón de diseño decorador.

@Repository
@Scope("singleton")
public class ClienteDAOImpl implements ClienteDAO {

	@Override
	public Cliente findById(Long id) {
		Cliente cliente = new Cliente();

		cliente.setId(id);
		cliente.setNombre("Jairo Cuartas");
		cliente.setEmail("prueba-demo@demo-spring.com");

		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> listClientes = new ArrayList<Cliente>();

		// Se buscan y se crean clientes.
		listClientes.add(findById(1L));
		listClientes.add(findById(2L));
		listClientes.add(findById(3L));

		return listClientes;
	}

}
