package co.edu.usbcali.demo.auto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vehiculo {

	private final static Logger log = LoggerFactory.getLogger(Vehiculo.class);

	private Motor motor;
	private String color;
	private Integer modelo;

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	@PostConstruct
	public void init() {
		log.info("Se creó el Vehiculo");
	}

	@PreDestroy
	public void destroy() {
		log.info("Se destruyó el vehículo");
	}

}
