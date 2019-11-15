package co.edu.usbcali.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacion")
public class OperacionesMatematicas {

	@GetMapping("/sumar/{n1}/{n2}")
	public ResultadoDTO sumar(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		return new ResultadoDTO(numeroUno + numeroDos);
	}

	@GetMapping("/restar/{n1}/{n2}")
	public ResultadoDTO restar(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		return new ResultadoDTO(numeroUno - numeroDos);
	}

	@GetMapping("/dividir/{n1}/{n2}")
	public ResponseEntity<?> dividir(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		
		if (numeroDos == 0) {
			return ResponseEntity.badRequest().body("Error división por cero");
		}

		return ResponseEntity.ok(new ResultadoDTO(numeroUno/numeroDos));
	}
}

class ResultadoDTO {
	private Integer valor;

	public ResultadoDTO(Integer valor) {
		super();
		this.valor = valor;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
}
