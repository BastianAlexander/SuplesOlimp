package cl.duoc.orden_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonPropertyOrder({"id", "cliente", "carritoId", "total", "estado", "fechaCreacion", "items"})
public class OrdenDTO {

    private Long id;
    private ClienteDTO cliente;
    private Long carritoId;
    private Integer total;
    private String estado;
    private LocalDateTime fechaCreacion;
    private List<OrdenItemDTO> items;
}