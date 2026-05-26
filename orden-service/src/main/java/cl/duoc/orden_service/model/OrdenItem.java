package cl.duoc.orden_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orden_item")
public class OrdenItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "orden_id")
    @NotNull(message = "El orden id no puede estar vacío")
    private Long ordenId;

    @Column(name = "producto_id")
    @NotNull(message = "El producto id no puede estar vacío")
    private Long productoId;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;

    @Column(name = "precio_unitario")
    @NotNull(message = "El precio unitario no puede estar vacío")
    private Integer precioUnitario;

    @NotNull(message = "El subtotal no puede estar vacío")
    private Integer subtotal;
}