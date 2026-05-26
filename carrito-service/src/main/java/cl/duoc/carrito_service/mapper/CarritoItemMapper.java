package cl.duoc.carrito_service.mapper;

import cl.duoc.carrito_service.dto.CarritoItemDTO;
import cl.duoc.carrito_service.dto.ProductoDTO;
import cl.duoc.carrito_service.model.CarritoItem;
import org.springframework.stereotype.Component;

@Component
public class CarritoItemMapper {

    public CarritoItemDTO toDTO(CarritoItem carritoItem, ProductoDTO productoDTO) {
        if (carritoItem == null) {
            return null;
        }

        CarritoItemDTO dto = new CarritoItemDTO();

        dto.setId(carritoItem.getId());
        dto.setProducto(productoDTO);
        dto.setCantidad(carritoItem.getCantidad());

        return dto;
    }

    public CarritoItem toEntity(CarritoItemDTO dto, Long carritoId) {
        if (dto == null) {
            return null;
        }

        CarritoItem carritoItem = new CarritoItem();

        carritoItem.setId(dto.getId());
        carritoItem.setCarritoId(carritoId);

        if (dto.getProducto() != null) {
            carritoItem.setProductoId(dto.getProducto().getId());
        }

        carritoItem.setCantidad(dto.getCantidad());

        return carritoItem;
    }
}