package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.TipoUsuario;
import co.edu.usbcali.bank.domain.Usuario;
import co.edu.usbcali.bank.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO entityToDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setTiusId( usuarioTipoUsuarioTiusId( usuario ) );
        usuarioDTO.setActivo( usuario.getActivo() );
        usuarioDTO.setClave( usuario.getClave() );
        usuarioDTO.setIdentificacion( usuario.getIdentificacion() );
        usuarioDTO.setNombre( usuario.getNombre() );
        usuarioDTO.setUsuUsuario( usuario.getUsuUsuario() );

        return usuarioDTO;
    }

    @Override
    public Usuario DTOToEntity(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setTipoUsuario( usuarioDTOToTipoUsuario( usuarioDTO ) );
        usuario.setActivo( usuarioDTO.getActivo() );
        usuario.setClave( usuarioDTO.getClave() );
        usuario.setIdentificacion( usuarioDTO.getIdentificacion() );
        usuario.setNombre( usuarioDTO.getNombre() );
        usuario.setUsuUsuario( usuarioDTO.getUsuUsuario() );

        return usuario;
    }

    @Override
    public List<Usuario> toUsuarios(List<UsuarioDTO> usuariosDTO) {
        if ( usuariosDTO == null ) {
            return null;
        }

        List<Usuario> list = new ArrayList<Usuario>( usuariosDTO.size() );
        for ( UsuarioDTO usuarioDTO : usuariosDTO ) {
            list.add( DTOToEntity( usuarioDTO ) );
        }

        return list;
    }

    @Override
    public List<UsuarioDTO> toUsuariosDTOs(List<Usuario> usuarios) {
        if ( usuarios == null ) {
            return null;
        }

        List<UsuarioDTO> list = new ArrayList<UsuarioDTO>( usuarios.size() );
        for ( Usuario usuario : usuarios ) {
            list.add( entityToDTO( usuario ) );
        }

        return list;
    }

    private Long usuarioTipoUsuarioTiusId(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }
        TipoUsuario tipoUsuario = usuario.getTipoUsuario();
        if ( tipoUsuario == null ) {
            return null;
        }
        Long tiusId = tipoUsuario.getTiusId();
        if ( tiusId == null ) {
            return null;
        }
        return tiusId;
    }

    protected TipoUsuario usuarioDTOToTipoUsuario(UsuarioDTO usuarioDTO) {
        if ( usuarioDTO == null ) {
            return null;
        }

        TipoUsuario tipoUsuario = new TipoUsuario();

        tipoUsuario.setTiusId( usuarioDTO.getTiusId() );

        return tipoUsuario;
    }
}
