package cl.duoc.perfil_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_auth_id")
    @NotNull(message = "Auth id no puede estar vacio")
    private Long auth ;

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

    private LocalDateTime ultimaModificacion;

}