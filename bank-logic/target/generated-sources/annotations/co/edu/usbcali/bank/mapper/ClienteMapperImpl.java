package co.edu.usbcali.bank.mapper;

import co.edu.usbcali.bank.domain.Cliente;
import co.edu.usbcali.bank.domain.TipoDocumento;
import co.edu.usbcali.bank.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: Eclipse JDT (IDE) 3.17.0.v20190306-2240, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO entityToDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setTdocId( clienteTipoDocumentoTdocId( cliente ) );
        clienteDTO.setActivo( cliente.getActivo() );
        clienteDTO.setClieId( cliente.getClieId() );
        clienteDTO.setDireccion( cliente.getDireccion() );
        clienteDTO.setEmail( cliente.getEmail() );
        clienteDTO.setNombre( cliente.getNombre() );
        clienteDTO.setTelefono( cliente.getTelefono() );

        return clienteDTO;
    }

    @Override
    public Cliente DTOToEntity(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setTipoDocumento( clienteDTOToTipoDocumento( clienteDTO ) );
        cliente.setActivo( clienteDTO.getActivo() );
        cliente.setClieId( clienteDTO.getClieId() );
        cliente.setDireccion( clienteDTO.getDireccion() );
        cliente.setEmail( clienteDTO.getEmail() );
        cliente.setNombre( clienteDTO.getNombre() );
        cliente.setTelefono( clienteDTO.getTelefono() );

        return cliente;
    }

    @Override
    public List<Cliente> toClientes(List<ClienteDTO> clientesDTO) {
        if ( clientesDTO == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( clientesDTO.size() );
        for ( ClienteDTO clienteDTO : clientesDTO ) {
            list.add( DTOToEntity( clienteDTO ) );
        }

        return list;
    }

    @Override
    public List<ClienteDTO> toClientesDTOs(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( entityToDTO( cliente ) );
        }

        return list;
    }

    private Long clienteTipoDocumentoTdocId(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }
        TipoDocumento tipoDocumento = cliente.getTipoDocumento();
        if ( tipoDocumento == null ) {
            return null;
        }
        Long tdocId = tipoDocumento.getTdocId();
        if ( tdocId == null ) {
            return null;
        }
        return tdocId;
    }

    protected TipoDocumento clienteDTOToTipoDocumento(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        TipoDocumento tipoDocumento = new TipoDocumento();

        tipoDocumento.setTdocId( clienteDTO.getTdocId() );

        return tipoDocumento;
    }
}
