package cl.duoc.producto_service.mapper;

import cl.duoc.producto_service.dto.ProductoDTO;
import cl.duoc.producto_service.model.Producto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setDescripcion(producto.getDescripcion());

        if (producto.getCategoria() != null) {
            dto.setIdCategoria(producto.getCategoria().getId());
            dto.setNombreCategoria(producto.getCategoria().getNombre());
        }

        return dto;
    }

    public List<ProductoDTO> toDTOList(List<Producto> productos) {
        return productos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}