package co.edu.usbcali.demo.autowired;

import java.util.List;

public interface ClienteDAO {

	public Cliente findById(Long id);

	public List<Cliente> findAll();
}
