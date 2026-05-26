package cl.duoc.orden_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonPropertyOrder({"id", "cliente", "items"})
public class CarritoDTO {

    private Long id;
    private ClienteDTO cliente;
    private List<CarritoItemDTO> items;
}