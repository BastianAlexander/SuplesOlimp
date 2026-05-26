package cl.duoc.resena_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
@Table(name = "resena")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id")
    @NotNull(message = "El producto id no puede estar vacío")
    private Long productoId;

    @Column(name = "cliente_id")
    @NotNull(message = "El cliente id no puede estar vacío")
    private Long clienteId;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 255, message = "El comentario no puede tener más de 255 caracteres")
    private String comentario;

    @NotNull(message = "La calificación no puede estar vacía")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    private Integer calificacion;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}