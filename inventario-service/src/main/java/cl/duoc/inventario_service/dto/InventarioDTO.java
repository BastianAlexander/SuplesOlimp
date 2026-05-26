package cl.duoc.inventario_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "producto", "stock"})
public class InventarioDTO {
    private Long id;
    private ProductoDTO producto;
    private Integer stock;
}
