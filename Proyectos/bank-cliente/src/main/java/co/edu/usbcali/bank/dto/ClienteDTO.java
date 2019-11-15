package co.edu.usbcali.bank.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ClienteDTO {

	@NotNull
	private Long clieId;

	@NotNull
	@Size(min = 1, max = 1)
	private String activo;

	@NotNull
	@Size(min = 5, max = 50)
	private String direccion;

	@NotNull
	@Size(min = 5, max = 50)
	@Email(message = "El correo debe estar escrito con @ y .")
	private String email;

	@NotNull
	@Size(min = 5, max = 100)
	private String nombre;

	@NotNull
	@Size(min = 5, max = 50)
	private String telefono;

	@NotNull
	private Long tdocId;

	public ClienteDTO() {
		super();
	}

	public ClienteDTO(@NotNull Long clieId, @NotNull @Size(min = 1, max = 1) String activo,
			@NotNull @Size(min = 5, max = 50) String direccion,
			@NotNull @Size(min = 5, max = 50) @Email(message = "El correo debe estar escrito con @ y .") String email,
			@NotNull @Size(min = 5, max = 100) String nombre, @NotNull @Size(min = 5, max = 50) String telefono,
			@NotNull Long tdocId) {
		super();
		this.clieId = clieId;
		this.activo = activo;
		this.direccion = direccion;
		this.email = email;
		this.nombre = nombre;
		this.telefono = telefono;
		this.tdocId = tdocId;
	}

	public Long getClieId() {
		return clieId;
	}

	public void setClieId(Long clieId) {
		this.clieId = clieId;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Long getTdocId() {
		return tdocId;
	}

	public void setTdocId(Long tdocId) {
		this.tdocId = tdocId;
	}
}
