package cl.duoc.pago_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonPropertyOrder({"id", "ordenId", "monto", "metodoPago", "estado", "fechaPago"})
public class PagoDTO {

    private Long id;
    private Long ordenId;
    private Integer monto;
    private String metodoPago;
    private String estado;
    private LocalDateTime fechaPago;
}