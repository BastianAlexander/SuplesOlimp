package cl.duoc.resena_service.mapper;

import cl.duoc.resena_service.dto.ClienteDTO;
import cl.duoc.resena_service.dto.ProductoDTO;
import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.model.Resena;
import org.springframework.stereotype.Component;

@Component
public class ResenaMapper {

    public ResenaDTO toDTO(Resena resena, ProductoDTO productoDTO, ClienteDTO clienteDTO) {
        if (resena == null) {
            return null;
        }

        ResenaDTO dto = new ResenaDTO();

        dto.setId(resena.getId());
        dto.setProducto(productoDTO);
        dto.setCliente(clienteDTO);
        dto.setComentario(resena.getComentario());
        dto.setCalificacion(resena.getCalificacion());
        dto.setFechaCreacion(resena.getFechaCreacion());

        return dto;
    }
}