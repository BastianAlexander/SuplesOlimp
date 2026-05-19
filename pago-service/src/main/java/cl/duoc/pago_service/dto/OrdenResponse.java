package cl.duoc.pago_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrdenResponse {

    private Long id;
    private Long perfilId;
    private BigDecimal total;
    private String estado;
    private LocalDateTime fecha;
}