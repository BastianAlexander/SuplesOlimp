package cl.duoc.orden_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orden")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    @NotNull(message = "El cliente id no puede estar vacío")
    private Long clienteId;

    @Column(name = "carrito_id")
    @NotNull(message = "El carrito id no puede estar vacío")
    private Long carritoId;

    @NotNull(message = "El total no puede estar vacío")
    private Integer total;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}