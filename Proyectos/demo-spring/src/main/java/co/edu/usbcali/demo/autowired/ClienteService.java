package co.edu.usbcali.demo.autowired;

import java.util.List;

public interface ClienteService {

	public Cliente findById(Long id);

	public List<Cliente> findAll();

}
