package cl.duoc.orden_service.dto;

import lombok.Data;

@Data
public class InventarioDTO {
    private Long id;
    private ProductoDTO producto;
    private Integer stock;
}