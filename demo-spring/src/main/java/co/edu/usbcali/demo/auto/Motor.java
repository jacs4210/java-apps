package co.edu.usbcali.demo.auto;

/* Solamente se puede crear una clase que represente al nombre del archivo .java*/
public class Motor {

	/*
	 * Desde la versión jdk 1.5, se incorporó el proceso auto-boxing para poder
	 * asignarle a una variable Integer, Boolean, etc, el valor que se desea, sin
	 * necesidad de instanciar.
	 */
	private String serial;
	private Integer cilindraje;
	private Boolean electrico;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Boolean getElectrico() {
		return electrico;
	}

	public void setElectrico(Boolean electrico) {
		this.electrico = electrico;
	}

}
