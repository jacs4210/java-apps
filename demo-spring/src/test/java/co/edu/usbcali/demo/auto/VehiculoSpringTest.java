package co.edu.usbcali.demo.auto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContextUno.xml")
class VehiculoSpringTest {

	@Autowired
	ApplicationContext context;

	@Test
	void test() {
		assertNotNull(context, "Contexto nulo");

		Vehiculo vehiculo = (Vehiculo) context.getBean("vehiculo");

		assertNotNull(vehiculo, "Vehículo nulo");
		assertNotNull(vehiculo.getMotor(), "Motor nulo");
	}

}
