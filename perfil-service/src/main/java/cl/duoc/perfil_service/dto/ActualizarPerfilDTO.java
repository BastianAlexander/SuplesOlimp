package cl.duoc.perfil_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ActualizarPerfilDTO {

    @NotBlank(message = "Nombre no puede estar vacio")
    @Size(max = 20, message = "Nombre debe tener un maximo de 20 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacio")
    @Size(max = 20, message = "Apellido debe tener un maximo de 20 caracteres")
    private String apellido;

    @NotBlank(message = "El telefono no puede estar vacio")
    @Size(max = 9, message = "Telefono debe tener un maximo de 9 caracteres")
    private String telefono;

    @NotBlank(message = "La direccion no puede estar vacio")
    @Size(max = 50, message = "La direccion debe tener un maximo de 50 caracteres")
    private String direccion;

}
