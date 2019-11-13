package co.edu.usbcali.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.dto.ClienteDTO;
import co.edu.usbcali.bank.mapper.ClienteMapper;
import co.edu.usbcali.bank.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	ClienteMapper clienteMapper;

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.findById(id);

		if (!clienteOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Cliente cliente = clienteOptional.get();
		ClienteDTO clienteDTO = clienteMapper.entityToDTO(cliente);

		return ResponseEntity.ok().body(clienteDTO);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAlll() {
		List<Cliente> clientes = clienteService.findAll();

		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		List<ClienteDTO> clienteDTOs = clienteMapper.toClientesDTOs(clientes);

		return ResponseEntity.ok().body(clienteDTOs);
	}

	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) {

		try {
			Cliente cliente = clienteMapper.DTOToEntity(clienteDTO);
			cliente = clienteService.save(cliente);
			clienteDTO = clienteMapper.entityToDTO(cliente);
			return ResponseEntity.ok().body(clienteDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody ClienteDTO clienteDTO) {

		try {
			Cliente cliente = clienteMapper.DTOToEntity(clienteDTO);
			cliente = clienteService.update(cliente);
			clienteDTO = clienteMapper.entityToDTO(cliente);
			return ResponseEntity.ok().body(clienteDTO);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {

		try {
			clienteService.deleteById(id);

			return ResponseEntity.ok().body("");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
