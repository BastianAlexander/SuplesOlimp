package cl.duoc.auth_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios_auth")
public class Auth {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Correo invalido")
    @NotBlank(message = "El correo no debe estar vacio.")
    private String correo;

    @Size(min = 6, message = "Minimo 6 caracteres")
    private String password;

    private Boolean activo;
    private LocalDateTime fechaCreacion;

}