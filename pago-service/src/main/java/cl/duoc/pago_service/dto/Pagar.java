package cl.duoc.pago_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Pagar {
    @NotNull(message = "El monto no puede estar vacío")
    @Positive(message = "El monto debe ser mayor a 0")
    private Integer monto;

    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoPago;
}
