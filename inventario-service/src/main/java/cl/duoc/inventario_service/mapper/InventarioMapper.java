package cl.duoc.inventario_service.mapper;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.dto.ProductoDTO;
import cl.duoc.inventario_service.model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    public InventarioDTO toDTO(Inventario inventario, ProductoDTO productoDTO) {
        if (inventario == null) {
            return null;
        }

        InventarioDTO dto = new InventarioDTO();

        dto.setId(inventario.getId());
        dto.setProducto(productoDTO);
        dto.setStock(inventario.getStock());

        return dto;
    }

    public Inventario toEntity(InventarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Inventario inventario = new Inventario();

        inventario.setId(dto.getId());

        if (dto.getProducto() != null) {
            inventario.setProductoId(dto.getProducto().getId());
        }

        inventario.setStock(dto.getStock());

        return inventario;
    }
}