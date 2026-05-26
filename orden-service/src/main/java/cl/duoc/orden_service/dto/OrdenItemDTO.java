package cl.duoc.orden_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "producto", "cantidad", "precioUnitario", "subtotal"})
public class OrdenItemDTO {

    private Long id;
    private ProductoDTO producto;
    private Integer cantidad;
    private Integer precioUnitario;
    private Integer subtotal;
}