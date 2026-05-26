package cl.duoc.inventario_service.dto;

import lombok.Data;

@Data
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Integer precio;
    private Long idCategoria;
    private String nombreCategoria;
}
