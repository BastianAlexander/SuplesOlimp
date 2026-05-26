package cl.duoc.carrito_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarritoItemDTO {

    private Long id;

    private ProductoDTO producto;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;
}