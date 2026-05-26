package cl.duoc.perfil_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "nombre", "apellido", "telefono", "direccion", "auth"})
public class PerfilDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private AuthDTO auth;
}
