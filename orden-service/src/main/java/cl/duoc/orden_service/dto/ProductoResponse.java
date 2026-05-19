package cl.duoc.orden_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private String marca;
    private BigDecimal precio;
    private Boolean activo;
}