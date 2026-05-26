package cl.duoc.orden_service.mapper;

import cl.duoc.orden_service.dto.ClienteDTO;
import cl.duoc.orden_service.dto.OrdenDTO;
import cl.duoc.orden_service.dto.OrdenItemDTO;
import cl.duoc.orden_service.model.Orden;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdenMapper {

    public OrdenDTO toDTO(Orden orden, ClienteDTO clienteDTO, List<OrdenItemDTO> items) {
        if (orden == null) {
            return null;
        }

        OrdenDTO dto = new OrdenDTO();

        dto.setId(orden.getId());
        dto.setCliente(clienteDTO);
        dto.setCarritoId(orden.getCarritoId());
        dto.setTotal(orden.getTotal());
        dto.setEstado(orden.getEstado());
        dto.setFechaCreacion(orden.getFechaCreacion());
        dto.setItems(items);

        return dto;
    }
}