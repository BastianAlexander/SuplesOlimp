package cl.duoc.pago_service.model;

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
@Table(name = "pago")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id")
    @NotNull(message = "La orden id no puede estar vacía")
    private Long ordenId;

    @NotNull(message = "El monto no puede estar vacío")
    private Integer monto;

    @Column(name = "metodo_pago")
    @NotBlank(message = "El método de pago no puede estar vacío")
    private String metodoPago;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;
}