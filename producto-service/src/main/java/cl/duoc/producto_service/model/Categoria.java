package cl.duoc.producto_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nombre no puede estar vacio.")
    @Size(max = 20, message = "Nombre solo puede tener un maximo de 20 caracteres")
    private String nombre;

}