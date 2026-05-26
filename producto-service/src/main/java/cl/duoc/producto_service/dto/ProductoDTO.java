package cl.duoc.producto_service.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "nombre", "precio", "descripcion", "idCategoria", "nombreCategoria"})
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre solo puede tener un máximo de 100 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Integer precio;

    @NotBlank(message = "Descripcion no puede estar vacio.")
    @Size(max = 150, message = "Descripcion solo puede tener un maximo de 150 caracteres")
    private String descripcion;

    @NotNull(message = "La categoría es obligatoria")
    private Long idCategoria;

    private String nombreCategoria;
}