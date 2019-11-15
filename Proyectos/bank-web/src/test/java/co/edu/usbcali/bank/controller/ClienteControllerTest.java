package co.edu.usbcali.bank.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.bank.dto.ClienteDTO;

class ClienteControllerTest {

	private final static Long clieId = 4680L;
	private final static String url = "http://localhost:8080/bank-web/api/cliente/";
	private final static Logger log = LoggerFactory.getLogger(ClienteControllerTest.class);

	@Test
	@DisplayName("save")
	void atest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();
			clienteDTO.setActivo("S");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("La umbria via pance");
			clienteDTO.setEmail("jcuartas@openintl.com");
			clienteDTO.setNombre("Jairo Cuartas");
			clienteDTO.setTdocId(1L);
			clienteDTO.setTelefono("305 898 46 23");

			Object object = restTemplate.postForObject(url + "save", clienteDTO, Object.class);

			assertNotNull(object);
		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getResponseBodyAsString());
		}
	}

	@Test
	@DisplayName("update")
	void btest() {
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo("S");
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("La umbria via pance");
		clienteDTO.setEmail("jairo.cuartas@openintl.com");
		clienteDTO.setNombre("Jairo Cuartas");
		clienteDTO.setTdocId(1L);
		clienteDTO.setTelefono("305 898 46 23");

		restTemplate.put(url + "update", clienteDTO);
	}

	@Test
	@DisplayName("delete")
	void ctest() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<ClienteDTO> request = new HttpEntity<>(new ClienteDTO());

			ResponseEntity<ClienteDTO> response = restTemplate.exchange(url + "delete/" + clieId.toString(),
					HttpMethod.DELETE, request, ClienteDTO.class);			
			// restTemplate.delete(url + "delete/" + clieId.toString());

		} catch (HttpStatusCodeException e) {
			log.info(e.getResponseBodyAsString());
			assertNull(e.getResponseBodyAsString());
		}
	}

//	@Test
//	@DisplayName("findById")
//	void dtest() {
//		RestTemplate restTemplate = new RestTemplate();
//
//		Object result = restTemplate.getForObject(url + "findById/" + clieId, Object.class);
//
//		assertNotNull(result);
//	}

}