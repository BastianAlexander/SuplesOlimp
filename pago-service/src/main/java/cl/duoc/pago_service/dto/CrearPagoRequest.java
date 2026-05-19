package cl.duoc.pago_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CrearPagoRequest {

    @NotNull(message = "El ordenId es obligatorio")
    private Long ordenId;

    @NotNull(message = "El monto es obligatorio")
    private BigDecimal monto;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodo;
}