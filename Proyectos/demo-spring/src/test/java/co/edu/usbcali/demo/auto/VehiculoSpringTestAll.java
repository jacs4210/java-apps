package co.edu.usbcali.demo.auto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContextUno.xml")
class VehiculoSpringTestAll {

	@Autowired
	Vehiculo vehiculo;

	@Test
	void test() {
		assertNotNull(vehiculo, "Vehiculo nulo");
		assertNotNull(vehiculo.getMotor(), "Motor nulo");
	}

}
