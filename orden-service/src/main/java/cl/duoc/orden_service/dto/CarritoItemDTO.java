package cl.duoc.orden_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "producto", "cantidad"})
public class CarritoItemDTO {

    private Long id;
    private ProductoDTO producto;
    private Integer cantidad;
}