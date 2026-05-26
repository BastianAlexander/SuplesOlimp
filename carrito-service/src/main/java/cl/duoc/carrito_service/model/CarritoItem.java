package cl.duoc.carrito_service.model;

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
@Table(name = "carrito_item")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carrito_id")
    @NotNull(message = "El carrito id no puede estar vacío")
    private Long carritoId;

    @Column(name = "producto_id")
    @NotNull(message = "El producto id no puede estar vacío")
    private Long productoId;

    @NotNull(message = "La cantidad no puede estar vacía")
    @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1")
    private Integer cantidad;
}