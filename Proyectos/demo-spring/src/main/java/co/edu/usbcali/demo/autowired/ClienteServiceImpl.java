package co.edu.usbcali.demo.autowired;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteDAO clienteDAO;

	@Override
	public Cliente findById(Long id) {
		return clienteDAO.findById(id);
	}

	@Override
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

}
