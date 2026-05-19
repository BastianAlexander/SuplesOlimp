package cl.duoc.orden_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarritoItemResponse {

    private Long id;
    private Long perfilId;
    private Long productoId;
    private Integer cantidad;
    private Boolean activo;
}