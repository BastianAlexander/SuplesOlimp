package cl.duoc.inventario_service.model;

import jakarta.persistence.*;
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
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producto_id")
    @NotNull(message = "El producto id no puede estar vacío")
    private Long productoId;

    @NotNull(message = "El stock no puede estar vacío")
    @Min(value = 0, message = "El stock no puede ser menor a 0")
    private Integer stock;



}