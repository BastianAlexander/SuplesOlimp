package cl.duoc.carrito_service.dto;

import lombok.Data;

@Data
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Integer precio;
}