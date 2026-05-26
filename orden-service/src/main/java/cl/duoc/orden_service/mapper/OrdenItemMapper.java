package cl.duoc.orden_service.mapper;

import cl.duoc.orden_service.dto.OrdenItemDTO;
import cl.duoc.orden_service.dto.ProductoDTO;
import cl.duoc.orden_service.model.OrdenItem;
import org.springframework.stereotype.Component;

@Component
public class OrdenItemMapper {

    public OrdenItemDTO toDTO(OrdenItem ordenItem, ProductoDTO productoDTO) {
        if (ordenItem == null) {
            return null;
        }

        OrdenItemDTO dto = new OrdenItemDTO();

        dto.setId(ordenItem.getId());
        dto.setProducto(productoDTO);
        dto.setCantidad(ordenItem.getCantidad());
        dto.setPrecioUnitario(ordenItem.getPrecioUnitario());
        dto.setSubtotal(ordenItem.getSubtotal());

        return dto;
    }
}