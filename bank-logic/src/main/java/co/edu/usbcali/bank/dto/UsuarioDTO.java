package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class UsuarioDTO {

	@NotNull
	private String usuUsuario;

	@NotNull
	@Size(min = 1, max = 1)
	private String activo;

	@NotNull
	@Size(min = 5, max = 20)
	private String clave;

	@NotNull
	@Positive
	private BigDecimal identificacion;

	@NotNull
	@Size(min = 5, max = 100)
	private String nombre;

	@NotNull
	@Positive
	private Long tiusId;

	public UsuarioDTO() {
		super();
	}

	public UsuarioDTO(@NotNull String usuUsuario, @NotNull @Size(min = 1, max = 1) String activo,
			@NotNull @Size(min = 5, max = 20) String clave, @NotNull @Positive BigDecimal identificacion,
			@NotNull @Size(min = 5, max = 100) String nombre, @NotNull @Positive Long tiusId) {
		super();
		this.usuUsuario = usuUsuario;
		this.activo = activo;
		this.clave = clave;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.tiusId = tiusId;
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BigDecimal getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTiusId() {
		return tiusId;
	}

	public void setTiusId(Long tiusId) {
		this.tiusId = tiusId;
	}

}
