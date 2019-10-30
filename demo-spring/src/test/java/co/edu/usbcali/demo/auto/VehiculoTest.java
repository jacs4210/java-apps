package co.edu.usbcali.demo.auto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class VehiculoTest {

	private final static Logger log = LoggerFactory.getLogger(VehiculoTest.class);

	@Test
	@DisplayName("Inyecci�n Motor")
	void inyeccionMotor() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/appContextUno.xml");
		assertNotNull(context, "El context esta nulo");

		Motor motor = (Motor) context.getBean("motor");
		assertNotNull(motor, "El motor es nulo");

		assertNotNull(motor.getSerial(), "Serial nulo");
		assertNotNull(motor.getCilindraje(), "Cilindraje nulo");
		assertNotNull(motor.getElectrico(), "Electrico nulo");

		// Impresi�n de resultados.
		log.info("Serial: " + motor.getSerial());
		log.info("Cilindraje: " + motor.getCilindraje());
		log.info("Electrico: " + motor.getElectrico());
	}

	@Test
	@DisplayName("Inyecci�n veh�culo")
	void inyeccionVehiculo() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/appContextUno.xml");
		assertNotNull(context, "El context esta nulo");

		Vehiculo vehiculo = (Vehiculo) context.getBean("vehiculo");
		assertNotNull(vehiculo, "El veh�culo es nulo");
		assertNotNull(vehiculo.getMotor(), "Motor nulo");
		assertNotNull(vehiculo.getColor(), "Color nulo");
		assertNotNull(vehiculo.getModelo(), "Modelo nulo");

		log.info("Color: " + vehiculo.getColor());
		log.info("Modelo: " + vehiculo.getModelo());

		Motor motor = vehiculo.getMotor();
		assertNotNull(motor, "Motor nulo");

		assertNotNull(motor.getSerial(), "Serial nulo");
		assertNotNull(motor.getCilindraje(), "Cilindraje nulo");
		assertNotNull(motor.getElectrico(), "Electrico nulo");

		// Impresi�n de resultados.
		log.info("Serial: " + motor.getSerial());
		log.info("Cilindraje: " + motor.getCilindraje());
		log.info("Electrico: " + motor.getElectrico());
	}

	@Test
	@DisplayName("Inyecci�n Singleton")
	void inyeccionSingleton() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/appContextUno.xml");
		assertNotNull(context, "El context esta nulo");

		Motor motorUno = (Motor) context.getBean("motor");
		assertNotNull(motorUno, "El motorUno es nulo");

		Motor motorDos = (Motor) context.getBean("motor");
		assertNotNull(motorDos, "El motorDos es nulo");

		assertEquals(motorUno, motorDos); 
	}

	/*@Test
	@DisplayName("Inyecci�n Prototype")
	void inyeccionPrototype() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/appContextUno.xml");
		assertNotNull(context, "El context esta nulo");

		Motor motorUno = (Motor) context.getBean("motor");
		assertNotNull(motorUno, "El motorUno es nulo");

		Motor motorDos = (Motor) context.getBean("motor");
		assertNotNull(motorDos, "El motorDos es nulo");

		assertNotEquals(motorUno, motorDos);
	}*/

}
