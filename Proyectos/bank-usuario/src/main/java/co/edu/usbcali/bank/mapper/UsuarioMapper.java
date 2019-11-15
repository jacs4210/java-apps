package co.edu.usbcali.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;

@Mapper
public interface UsuarioMapper {

	@Mapping(source = "tipoUsuario.tiusId", target = "tiusId")
	UsuarioDTO entityToDTO(Usuario usuario);

	@Mapping(source = "tiusId", target = "tipoUsuario.tiusId")
	Usuario DTOToEntity(UsuarioDTO usuarioDTO);

	List<Usuario> toUsuarios(List<UsuarioDTO> usuariosDTO);

	List<UsuarioDTO> toUsuariosDTOs(List<Usuario> usuarios);

}
