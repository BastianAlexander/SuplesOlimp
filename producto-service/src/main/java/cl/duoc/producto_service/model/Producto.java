package cl.duoc.producto_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacio.")
    @Size(max = 100, message = "Nombre solo puede tener un maximo de 100 caracteres")
    private String nombre;


    @NotNull(message = "El precio no puede estar vacio.")
    private Integer precio;

    @NotBlank(message = "Descripcion no puede estar vacio.")
    @Size(max = 150, message = "Descripcion solo puede tener un maximo de 150 caracteres")
    private String descripcion;


    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @NotNull(message = "Categoria no puede estar vacio.")
    private Categoria categoria;

}
