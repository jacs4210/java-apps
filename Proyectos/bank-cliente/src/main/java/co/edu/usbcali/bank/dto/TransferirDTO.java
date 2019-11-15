package co.edu.usbcali.bank.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TransferirDTO {

	@NotNull
	@Size(min = 9, max = 19)
	private String cuenIdOrigen;

	@NotNull
	@Size(min = 9, max = 19)
	private String cuenIdDestino;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@Size(min = 2, max = 50)
	private String usuUsuario;

	public TransferirDTO() {
		super();
	}

	public TransferirDTO(@NotNull @Size(min = 9, max = 19) String cuenIdOrigen,
			@NotNull @Size(min = 9, max = 19) String cuenIdDestino, @NotNull @Positive BigDecimal valor,
			@NotNull @Size(min = 2, max = 50) String usuUsuario) {
		super();
		this.cuenIdOrigen = cuenIdOrigen;
		this.cuenIdDestino = cuenIdDestino;
		this.valor = valor;
		this.usuUsuario = usuUsuario;
	}

	public String getCuenIdOrigen() {
		return cuenIdOrigen;
	}

	public void setCuenIdOrigen(String cuenIdOrigen) {
		this.cuenIdOrigen = cuenIdOrigen;
	}

	public String getCuenIdDestino() {
		return cuenIdDestino;
	}

	public void setCuenIdDestino(String cuenIdDestino) {
		this.cuenIdDestino = cuenIdDestino;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

}
