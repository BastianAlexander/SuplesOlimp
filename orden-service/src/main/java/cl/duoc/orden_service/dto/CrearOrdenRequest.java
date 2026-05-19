package cl.duoc.orden_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearOrdenRequest {

    @NotNull(message = "El perfilId es obligatorio")
    private Long perfilId;
}