package cl.duoc.auth_service.dto;

import lombok.Data;

@Data
public class ActualizarAuthDto {
private String contrasennaActual;
private String correo;
private String password;
}
